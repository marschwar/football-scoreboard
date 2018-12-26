# Prerequisites
```
npm install -g serverless
```

# Deploy to AWS
Set up credentials
```
serverless config credentials --provider aws --key EXAMPLE --secret EXAMPLEKEY
```

```
./gradlew deploy
```