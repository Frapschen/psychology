# psychology
a web project

## 文章模块

### 场景
1. 成功登录的角色查看文章列表，如果有多个，以10个文章为分页。
2. 成功登录的点击文章列表中的某一个文章，进入文章正文，同时可以看到最多10条属于该文章的评论且按最新时间倒序排列，
用户可以在第一条评论前添加再一条评论。
3. 成功登录的角色查看文章列表且可以删除选中的文章。
4. 成功登录的角色查看文章列表且可以添加一篇新的文章。
5. 成功登录的角色查看文章列表且可以修改选择的文章。

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
role,int,角色，1为管理源，0为普通用户
```

#### 实体sql

```sql
-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `context` mediumblob,
  `created_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for conmment
-- ----------------------------
DROP TABLE IF EXISTS `conmment`;
CREATE TABLE `conmment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contxt` blob,
  `user_id` int(11) NOT NULL,
  `article_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `article_pk` (`article_id`),
  KEY `user_pk` (`user_id`),
  CONSTRAINT `article_pk` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE,
  CONSTRAINT `user_pk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
```

### api接口

#### 显示所有文章
* url：http://ip:8089/psychology/v1/article/list
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

#### 查询指定id的文章
* url：http://ip:8089/psychology/v1/article/{id}
* 方法：get
* 权限：管理员，用户
* 返回参数：
```json5
{
  "article": {}, //一个文章的实体
  "conmment": {
    "conmments": [{},{}],  //一些conmment实体
    page: 1,
    size: 10,
    total: 100
  }
}
```
