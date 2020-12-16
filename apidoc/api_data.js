define({ "api": [
  {
    "type": "post",
    "url": "addfriend",
    "title": "添加好友",
    "description": "<p>添加好友接口</p>",
    "group": "好友",
    "name": "添加好友",
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>用户ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "friendId",
            "description": "<p>预好友ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>验证消息</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "groupname",
            "description": "<p>分组名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "note",
            "description": "<p>好友备注</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>响应描述</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "data",
            "description": "<p>返回相关信息，成功的时候才存在</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "预添加成功-示例:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\":1,\n  \"message\": \"success\"\n  \"data\": \"添加成功\"\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "int",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>响应描述</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "预添加失败-示例：",
          "content": " HTTP/1.1 200 OK\n{\n  \"code\":1,\n  \"message\": \"添加失败\"\n  \"data\":null\n}",
          "type": "json"
        }
      ]
    },
    "filename": "back-end/src/main/java/com/example/chatonline/controller/UserController.java",
    "groupTitle": "好友"
  },
  {
    "type": "post",
    "url": "PreAddfriend",
    "title": "预添加好友",
    "description": "<p>预添加好友接口</p>",
    "group": "好友",
    "name": "预添加好友",
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>用户ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "friendId",
            "description": "<p>预好友ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>响应描述</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "data",
            "description": "<p>返回用户分组信息，成功的时候才存在</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "预添加成功-示例:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\":1,\n  \"message\": \"success\"\n  \"data\": [group1,group2...]\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "int",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>响应描述</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "预添加失败-示例：",
          "content": " HTTP/1.1 200 OK\n{\n  \"code\":1,\n  \"message\": \"该用户已在好友列表中\"\n  \"data\":null\n}\n\n HTTP/1.1 200 OK\n{\n  \"code\":-1,\n  \"message\": \"未能查询到分组信息\"\n  \"data\":null\n }",
          "type": "json"
        }
      ]
    },
    "filename": "back-end/src/main/java/com/example/chatonline/controller/UserController.java",
    "groupTitle": "好友"
  },
  {
    "type": "post",
    "url": "find",
    "title": "查找用户",
    "description": "<p>查找用户接口</p>",
    "group": "用户",
    "name": "查找用户",
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>用户ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>响应描述</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "data",
            "description": "<p>返回用户基本信息，成功的时候才存在</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "查找成功-示例:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\":1,\n  \"message\": \"success\"\n  \"data\": {\n\n  }\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "int",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>响应描述</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "查找失败-示例：",
          "content": " HTTP/1.1 200 OK\n{\n  \"code\":0,\n  \"message\": \"未查询到该用户\"\n  \"data\":null\n}",
          "type": "json"
        }
      ]
    },
    "filename": "back-end/src/main/java/com/example/chatonline/controller/UserController.java",
    "groupTitle": "用户"
  },
  {
    "type": "post",
    "url": "register",
    "title": "注册",
    "description": "<p>用户注册接口</p>",
    "group": "登录注册",
    "name": "注册",
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "nickname",
            "description": "<p>昵称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>密码</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>响应描述</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "data",
            "description": "<p>返回注册ID，成功的时候才存在</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "登录成功-示例:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\":1,\n  \"message\": \"success\"\n  \"data\": userId\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "int",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>响应描述</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "登录失败-示例：",
          "content": " HTTP/1.1 200 OK\n{\n  \"code\":0,\n  \"message\": \"注册失败\"\n  \"data\":null\n}",
          "type": "json"
        }
      ]
    },
    "filename": "back-end/src/main/java/com/example/chatonline/controller/UserController.java",
    "groupTitle": "登录注册"
  },
  {
    "type": "post",
    "url": "login",
    "title": "登录",
    "description": "<p>用户登录接口</p>",
    "group": "登录注册",
    "name": "登录",
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>账号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>密码</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>响应描述</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据，成功的时候才存在</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "登录成功-示例:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\":1,\n  \"message\": \"success\"\n  \"data\":{}\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "int",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>响应描述</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "登录失败-示例：",
          "content": " HTTP/1.1 200 OK\n{\n  \"code\":0,\n  \"message\": \"登录失败\"\n  \"data\":null\n}",
          "type": "json"
        }
      ]
    },
    "filename": "back-end/src/main/java/com/example/chatonline/controller/UserController.java",
    "groupTitle": "登录注册"
  }
] });
