# native-api-samples

Sample projects using GraalVM native-image

## Example

```
$ curl -XPOST localhost:8080/kuromoji -H 'Content-Type: application/json' -d '{"text": "僕はクマ"}'
{
    "tokens": [
        "僕",
        "は",
        "クマ"
    ]
}
```
