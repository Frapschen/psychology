# Build

## pull

```shell
git clone https://github.com/Frapschen/psychology.git
```

### maven package

```shell
mvn clean package -Dmaven.test.skip=true
```

## docker build

```shell
docker build -t psychology:0.1 .
```

### doker run

```shell
docker run  -d -p 8089:8089 --name psychology psychology:x.x
```

