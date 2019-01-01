# Prerequisites
```
npm install -g serverless
```

# Deploy to AWS
Create inline policy and assign it to a group which is assigned to a project user.
```
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "Stmt1546172899000",
            "Effect": "Allow",
            "Action": [
                "iam:CreateRole",
                "iam:PutRolePolicy",
                "iam:DeleteRolePolicy",
                "iam:DeleteRole",
                "cloudformation:CreateStack",
                "cloudformation:UpdateStack",
                "cloudformation:DeleteStack",
                "cloudformation:DescribeStackEvents",
                "cloudformation:DescribeStackResource",
                "cloudformation:ValidateTemplate",
                "apigateway:POST",
                "apigateway:DELETE",
                "apigateway:PUT",
                "apigateway:GET"
            ],
            "Resource": [
                "*"
            ]
        }
    ]
}
```
Set up credentials
```
serverless config credentials --provider aws --key EXAMPLE --secret EXAMPLEKEY
```

```
./gradlew deploy
```