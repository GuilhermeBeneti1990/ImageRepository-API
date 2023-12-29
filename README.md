# Image Repository API
## Image API
### Get - Image By ID

#### http://localhost:8080/v1/images/92907a3b-2f3c-4120-96e9-f991483ad7f0

### GET - Search Image (Get with specification)
### Return: List of images

#### http://localhost:8080/v1/images?name=Teste
#### http://localhost:8080/v1/images?extesion=JPEG
#### http://localhost:8080/v1/images?tags=test

### POST - Save Image
### Return: 201 created

#### http://localhost:8080/v1/images
Multipart Form (form-data):
  ##### name: String
  ##### tags: String
  (example: tag01, tag02, tag03)
  ##### file: File

## User API
### Post - Save user
### Return: 201 created

#### http://localhost:8080/v1/users
Raw JSON:
  ##### name: String
  ##### email: String
  ##### password: String

### Post - User authenticate
### Return: accessToken

#### http://localhost:8080/v1/users/auth
Raw JSON:
  ##### email: String
  ##### password: String
