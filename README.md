# psychology
a web project

## 文章模块

### 场景
#### 1.成功登录的角色查看文章列表，如果有多个，以10个文章为分页。

#### 2.成功登录的点击文章列表中的某一个文章，进入文章正文，同时可以看到最多10条属于该文章的评论且按最新时间倒序排列。

#### 3.成功登录的角色可以文章正文下方添加一条评论。

#### 4.成功登录的角色查看文章列表且可以删除选中的文章。

#### 5.成功登录的角色查看文章列表且可以添加一篇新的文章。

#### 6.成功登录的角色查看文章列表且可以修改选择的文章。

#### 7.成功登录的角色查看文章正文且可以删除一条评论。

### 参与角色
管理员，用户

### 权限
在所有的场景中，管理员可以完成所有场景，用户可以完成1-2场景

### 参与实体
文章、评论、用户

### 实体

1. 文章实体
```
table_name:article
colums:
id,int,主键
name,varchar(255),名字
introduce,varchar(500)，简介
context,mediumblob,内容
created_time,varchar(100),创建时间
```

2. 评论
```
table_name:conmment
colums:
id,int,主键
contxt,blob,内容
created_time,varchar(100),创建时间
user_id,int,用户id外键
article_id,int,文章id外键
```

3. 用户
```
table_name:user
colums:
id,int,主键
username,varchar(100),用户名
password,varchar(100),密码
image,blob,图片
role,int,角色，1为管理源，0为普通用户
```

#### 实体sql

```sql
-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `image` mediumblob,
  `role` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `introduce` text,
  `content` text,
  `created_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for conmment
-- ----------------------------
DROP TABLE IF EXISTS `conmment`;
CREATE TABLE `conmment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text,
  `user_id` int(11) NOT NULL,
  `article_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `article_pk` (`article_id`),
  KEY `user_pk` (`user_id`),
  CONSTRAINT `article_pk` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE,
  CONSTRAINT `user_pk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

### api接口

#### 1.显示所有文章

* 情景：成功登录的角色查看文章列表，如果有多个，以10个文章为分页。
* url：http://localhost:8089/psychology/v1/article/list
* 方法：get
* 权限：无
* query参数：
```
page,int,页数,默认为0
size,int,大小,默认为10
```
* 返回参数：
```json
{
  "articles": [{},{}], //article的实体列表
  "page": 0,
  "size": 10,
  "total": 100
}
```

#### 2.查询指定id的文章

* 情景：成功登录的点击文章列表中的某一个文章，进入文章正文，同时可以看到最多10
  条属于该文章的评论且按最新时间倒序排列。

* url：http://localhost:8089/psychology/v1/article/{id}
* 方法：get
* 权限：管理员，用户
* 返回参数：
```json
{
  "article": {}, //一个article实体
  "conmment": {
    "conmments": [{},{}],  //一些conmment实体
    "page": 0,
    "size": 10,
    "total": 100
  }
}
```
##### 2.1 查询评论

* 情景：用户在文章正文，查看下一页评论
* url：http://localhost:8089/psychology/v1/comment
* 方法：get
* 权限：管理员，用户
* qury参数：

```
article-id,int,文章id
page,int,页数,默认为0
size,int,大小,默认为10
```

* 返回参数：

```
{
  "conmment": {
    "conmments": [{},{}],  //一些conmment实体
    "page": 0,
    "size": 10,
    "total": 100
  }
}
```



#### 3.评论指定的文章

* 情景：成功登录的角色可以文章正文下方添加一条评论。
* url：http://localhost:8089/psychology/v1/comment/{article-id}/{user-id}
* 方法：put
* 权限：管理员，用户，老师
* requestbody：
```json
{} //一个comment 实体
```
* 返回参数：
```json
{
  "code": 200,
  "message": "sucess"
}
```

#### 4.删除选中文章

情景：成功登录的角色查看文章列表且可以删除选中的文章。
* url：http://localhost:8089/psychology/v1/article/{id}
* 方法：delete
* 权限：管理员
* 返回参数：
```json
{
  "code": 200,
  "message": "sucess"
}
```

#### 5. 添加一篇文章

情景：成功登录的角色查看文章列表且可以添加一篇新的文章。
* url http://localhost:8089/psychology/v1/article
* 方法：put
* 权限：管理员
* requestbody:
```json
{} //一个文章的实体
```
* 返回参数：
```json
{
  "code": 200,
  "message": "sucess"
}
```

#### 6. 修改一篇文章

* 情景：成功登录的角色查看文章列表且可以修改选择的文章。
* url：http://localhost:8089/psychology/v1/article
* 方法：post
* 权限：管理员
* requestbody:
````json
{}  //一个文章的实体(必须包含一个存在的文章id)
````
* 返回参数：
```json
{
  "code": 200,
  "message": "sucess"
}
```
#### 7. 删除评论

* 情景：成功登录的角色查看文章正文且可以删除一条评论。
* url：http://localhost:8089/psychology/v1/comment/{id}
* 方法：delete
* 权限：管理员
* 返回参数：
```json
{
  "code": 200,
  "message": "sucess"
}
```

## 用户模块-end

### 场景

#### 1.登录

#### 2.注册

#### 3.以一个列表查看所有的用户(支持分页)

#### 4.删除用户

### 参与角色

管理员，用户

### 参与实体

用户，验证码

### 权限

管理员有登录、查看所有、删除用户；用户有登录场景；注册不需要场景且只能注册为用户

### 实体

1. 用户

```
id,int,用户id
username,varchar(255),用户名
password,varchar(255),密码
introduce,text,简介
phone,int,手机
image,mdeiumblob,图像
role,int,权限，0,代表管理员，1代表用户，2代表老师
```

2. 验证码

```
id,int,验证码id
code,varchar,验证码
used,int,是否用过，1代表用过，0为默认值
```

3. 预约

```
id,int,主键
context,text,内容
user_id,用户id
expert_id
```



#### 实体sql

```mysql
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT '',
  `introduce` text,
  `image` mediumblob,
  `role` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `code`;
CREATE TABLE `code` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `used` int(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

### api接口

#### 0.验证码-end

* 请求：发起登录活动
* url：http://localhost:8089/psychology/v1/common/code
* 方法：get
* 权限：无
* 返回参数：

```json
一张图片
```

#### 1.登录-end

* 情景：发起一个登录活动

* url：http://localhost:8089/psychology/v1/user/login
* 方法：post
* 权限：无
* form参数

```
username=aaaa
password=aaaa
code=aaaa
```

* 返回参数：
```json
{
    "code":200,
    "data": user object 其中imageByte 是图像的二进制数据，用<img>标签可以显示
}
```

验证码错误：

```
{
    "code":412,
    "user-id": -1
}
```

密码或账号出错

```
{
    "code":401,
    "user-id": -1
}
```



#### 2.注册-end

* 情景：注册一个账号
* url：http://localhost:8089/psychology/v1/user/register
* 方法：put
* 权限：无
* form表单

```
name=frasp
username=aaaa
password=aaaa
introduce=aaaa
phone=1213123
role=1 or 2
image=images.png
code=aaaa
```

* 返回参数

```
{
  "code": 200
}
```

验证码出错

```
{
  "code": 412
}
```

注册失败

```
{
  "code": 413
}
```



#### 3.查看所有的用户-end

* 情景：以一个列表查看所有的用户(支持分页)
* url：http://localhost:8089/psychology/v1/user
* 方法：get
* 权限：管理员
* 请求参数：

```
page,int,页数,默认为1
size,int,大小,默认为10
user_id,int,用户id
```

* 返回参数：

```
{
    "pageNum": 1,
    "pageSize": 5,
    "size": 5,
    "startRow": 1,
    "endRow": 5,
    "total": 13,
    "pages": 3,
    "list": [
        {
            "id": 1,
            "name": null,
            "username": "啊手动阀",
            "password": "123",
            "introduce": null,
            "phone": null,
            "image": null,
            "imageByte": null,
            "role": 0
        },
        {
            "id": 2,
            "name": null,
            "username": "士大夫",
            "password": "123456",
            "introduce": null,
            "phone": null,
            "image": null,
            "imageByte": null,
            "role": 0
        },
        {
            "id": 6,
            "name": null,
            "username": "啊手动阀",
            "password": "asdf",
            "introduce": null,
            "phone": "",
            "image": null,
            "imageByte": null,
            "role": 1
        },
        {
            "id": 7,
            "name": null,
            "username": "asdf",
            "password": "ASDF",
            "introduce": null,
            "phone": "",
            "image": null,
            "imageByte": null,
            "role": 1
        },
        {
            "id": 8,
            "name": null,
            "username": "ASDFX",
            "password": "ASDF",
            "introduce": null,
            "phone": "",
            "image": null,
            "imageByte": null,
            "role": 1
        }
    ],
    "prePage": 0,
    "nextPage": 2,
    "isFirstPage": true,
    "isLastPage": false,
    "hasPreviousPage": false,
    "hasNextPage": true,
    "navigatePages": 8,
    "navigatepageNums": [
        1,
        2,
        3
    ],
    "navigateFirstPage": 1,
    "navigateLastPage": 3,
    "firstPage": 1,
    "lastPage": 3
}
```

#### 4.删除用户-end  

* 情景：删除一个用户
* url：http://localhost:8089/psychology/v1/user/{id}
* 方法：delete
* 权限：管理员
* 参数

```
user_id,int,管理员id
id,int,要删除的用户id
```



* 返回参数

```
{
  "code": 200,
  "message": "sucess"
}
```



## 预约模块-end

### 场景

#### 1.成功登录角色可以查看老师列表

#### 2.成功登录角色可以预约一个老师

#### 3.成功登录的老师可以查看自己预约的老师

#### 4.成功登录的角色可以查看自己被预约的情况

#### 5.成功登录的角色可以完成一个预约

### 参与角色

用户，老师

### 参与实体

用户，预约表

### 权限

老师角色可以完成4，5

用户角色可以完成1，2，3

### 实体

1. 预约表

```
id,int,主键
time,varchar(255),预约时间
teacher_id,int,老师用户的id
user_id,int,用户id
finished,int，1代表已经完成，0代表未完成
```

#### 实体sql

```sql
CREATE TABLE `appointment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` varchar(255) NOT NULL,
  `user_id` int(255) NOT NULL,
  `teacher_id` int(255) NOT NULL,
  `finished` int(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `teacher_pk` (`teacher_id`),
  CONSTRAINT `teacher_pk` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

### api接口

#### 1. 查看老师列表-end

* 情景：用户查看老师列表
* url：http://localhost:8089/psychology/v1/appointment/list
* 方法：get
* 权限：无
* 请求参数

```
page,int,页数,默认为1
size,int,大小,默认为10
```

* 返回参数：

```
{
  "teachers": [{},{}], //user中对应老师的实体列表
  "page": 0,
  "size": 10,
  "total": 100
}
```

#### 2. 预约一个老师-end

* 情景：用户预约一个老师
* url：http://localhost:8089/psychology/v1/appointment/{user_id}/{teacher_id}
* 方法：put
* 权限：用户
* 请求参数

```
user_id,int,用户id
teacher_id,int,老师id
```

* 返回参数：

```
{
  "code": 200,
  "message": "sucess"
}
```

错误

```
{
  "code": 413
}
```

#### 3.查看自己预约-end

* 场景：用户查看自己的预约
* url：http://localhost:8089/psychology/v1/appointment/{user_id}/user
* 方法：get
* 权限：用户
* 请求参数：

```
page,int,页数,默认为0
size,int,大小,默认为10
```

* 返回参数：

```
{
  "teachers": [{},{}], //user中对应老师的实体列表
  "page": 0,
  "size": 10,
  "total": 100
}
```

#### 4. 查看预约自己的用户-end

* 场景：老师查看预约自己的用户
* url：http://localhost:8089/psychology/v1/appointment/{teacher_id}/teacher
* 方法：get
* 权限：老师
* 请求参数：

```
page,int,页数,默认为0
size,int,大小,默认为10
```

* 返回参数：

```
{
  "users": [{},{}], //user中对应老师的实体列表
  "page": 0,
  "size": 10,
  "total": 100
}
```

#### 5. 完成预约-end

* 情景：老师完成一个预约
* url：http://localhost:8089/psychology/v1/appointment/{appointment_id}/finish/{teacher_id}
* 方法：post
* 权限：老师
* 返回参数：

```
{
  "code": 200,
  "message": "sucess"
}
```

# 测试

* 先连接VPN
* localhost=192.168.123.188

1. 数据库连接测试：
```
http://localhost:8089/psychology/v1/user/ping
```