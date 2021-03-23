# psychology
a web project

## 文章模块

### 场景
1. 成功登录的角色查看文章列表，如果有多个，以10个文章为分页。
2. 成功登录的点击文章列表中的某一个文章，进入文章正文，同时可以看到最多10条属于该文章的评论且按最新时间倒序排列。
3. 成功登录的角色可以文章正文下方添加一条评论。
4. 成功登录的角色查看文章列表且可以删除选中的文章。
5. 成功登录的角色查看文章列表且可以添加一篇新的文章。
6. 成功登录的角色查看文章列表且可以修改选择的文章。
7. 成功登录的角色查看文章正文且可以删除一条评论。

### 参与角色
管理员，用户

### 权限
在所有的场景中，管理员可以完成所有场景，用户可以完成1-2场景

###参与实体
文章、评论、用户
#### 实体

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
  `role` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `content` mediumblob,
  `created_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for conmment
-- ----------------------------
DROP TABLE IF EXISTS `conmment`;
CREATE TABLE `conmment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` blob,
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
* url：http://localhost:8089/psychology/v1/article/list
* 方法：get
* 权限：无
* query参数：
```
page,int,页数,默认为1
size,int,大小,默认为10
```
* 返回参数：
```json5
{
  "article": [{},{}], //article的实体列表
  "page": 1,
  "size": 10,
  "total": 100
}
```

#### 2.查询指定id的文章
* url：http://localhost:8089/psychology/v1/article/{id}
* 方法：get
* 权限：管理员，用户
* 返回参数：
```json
{
  "article": {}, //一个article实体
  "conmment": {
    "conmments": [{},{}],  //一些conmment实体
    "page": 1,
    "size": 10,
    "total": 100
  }
}
```
### 3.评论指定的文章
* url：http://localhost:8089/psychology/v1/comment/{article-id}
* 方法：put
* 权限：管理员，用户
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

### 4.删除选中文章
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

### 5. 添加一篇文章
* url http://localhost:8089/psychology/v1/article
* 方法：put
* 权限：管理员
* requestbody
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