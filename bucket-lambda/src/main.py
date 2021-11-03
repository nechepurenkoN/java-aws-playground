from dataclasses import dataclass
from typing import Dict

import boto3
import logging

s3 = boto3.client("s3")


@dataclass
class FileStruct:
    bucket_name: str
    filename: str


def unpack_record(record: Dict) -> FileStruct:
    """
    :param record
    :return: a struct with object's bucket and file names
    """
    return FileStruct(record["s3"]["bucket"]["name"], record["s3"]["object"]["key"])


def is_new(file: FileStruct) -> bool:
    """
    :param file
    :return: bool, check if the file is new, i.e. there are only one version of the file in the bucket
    """
    list_object_version_response = s3.list_object_versions(Prefix=file.filename, Bucket=file.bucket_name)
    return len(list_object_version_response["Versions"]) == 1


def duplicate(file: FileStruct):
    """
    Puts the second version of the newly created file
    :param file:
    :return:
    """
    file_content = s3.get_object(Bucket=file.bucket_name, Key=file.filename)['Body'].read()
    logging.info(f"Creating the second version of {file.filename} in {file.bucket_name}")
    s3.put_object(Bucket=file.bucket_name, Key=file.filename, Body=file_content)


def lambda_handler(event, context):
    for record in event["Records"]:
        file = unpack_record(record)
        if is_new(file):
            duplicate(file)
        else:
            logging.info(f"{file.filename} has several versions in {file.bucket_name}")