# How to use this "oauth"

1. Checkout this repository.

2. create mysql database with database.sql

3. update mysql connection in applocation.properties

4. get code in browser
localhost:8080/oauth/authorize?client_id=wang&response_type=code&redirect_uri=http://www.facebook.com

5. replace the code and get the access-token
curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'grant_type=authorization_code&code=g8dfRP&redirect_uri=http://www.facebook.com' "http://wang:secret@localhost:8080/oauth/token"


note:
client_id is stored in  oauth_client_details
oauth_access_token stores the tokens

also grant_type=client_credentials: 
curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'grant_type=client_credentials&code=PshJa5&redirect_uri=http://www.facebook.com' "http://wang:1234@localhost:8080/oauth/token"
 


also try json:
 curl 'http://localhost:8080/oauth/token' -i -X POST -H 'Accept: application/json'\
 -H 'Content-Type: application/x-www-form-urlencoded' \
 -d 'client_id=wang&client_secret=1234&grant_type=authorization_code&response_type=token&token_format=opaque&code=eQf6lZ&redirect_uri=http://www.facebook.com'