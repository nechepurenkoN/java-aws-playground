import boto3

s3 = boto3.client("s3")


def handle(event, context):
    print(event)
    # s3.put_object(
    #     Bucket="123",
    #     Key="123",
    #     Body="321"
    # )
