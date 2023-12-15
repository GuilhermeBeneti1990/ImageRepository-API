# Image Repository API

## Get - Image By ID

### http://localhost:8080/v1/images/92907a3b-2f3c-4120-96e9-f991483ad7f0

## GET - Search Image (Get with specification)

### http://localhost:8080/v1/images?name=Teste
### http://localhost:8080/v1/images?extesion=JPEG
### http://localhost:8080/v1/images?tags=test

## POST - Save Image

### http://localhost:8080/v1/images
Multipart Form:
  #### name: String
  #### tags: String
  (example: tag01, tag02, tag03)
  #### file: File
