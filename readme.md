# How to use this "oauth"

1. Checkout this repository.

2. create mysql database with database.sql

3. update applocation.properties

4. get code 
localhost:8080/oauth/authorize?client_id=wang&response_type=code&redirect_uri=http://www.facebook.com

5. replace the code and get the access-token
curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'grant_type=authorization_code&code=g8dfRP&redirect_uri=http://www.facebook.com' "http://wang:secret@localhost:8080/oauth/token"

client_id is stored in  oauth_client_details
oauth_access_token stores the tokens
 