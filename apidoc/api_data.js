define({ "api": [
  {
    "type": "post",
    "url": "groupfriends",
    "title": "好友列表",
    "description": "<p>好友列表接口</p>",
    "group": "好友",
    "name": "好友列表",
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>用户Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "groupName",
            "description": "<p>分组名</p>"
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
            "description": "<p>返回分组内的好友信息，成功的时候才存在</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "查询成功-示例:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\":1,\n  \"message\": \"success\",\n  \"data\": 好友列表,\n}",
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
          "title": "查询失败-示例：",
          "content": " HTTP/1.1 200 OK\n{\n  \"code\":0,\n  \"message\": \"未查询到该用户好友信息\",\n  \"data\":null,\n}",
          "type": "json"
        }
      ]
    },
    "filename": "back-end/src/main/java/com/example/chatonline/controller/UserController3.java",
    "groupTitle": "好友"
  },
  {
    "type": "post",
    "url": "groupMove",
    "title": "好友移动",
    "description": "<p>好友移动接口</p>",
    "group": "好友",
    "name": "好友移动",
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>用户Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "preGroupName",
            "description": "<p>移动前分组名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "postGroupName",
            "description": "<p>移动后分组名</p>"
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
            "description": "<p>返回信息，成功的时候才存在</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "移动成功-示例:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\":1,\n  \"message\": \"success\",\n  \"data\": \"移动好友分组成功\",\n}",
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
          "title": "移动失败-示例：",
          "content": " HTTP/1.1 200 OK\n{\n  \"code\":0,\n  \"message\": \"好友分组移动失败\"\n  \"data\":null\n}",
          "type": "json"
        }
      ]
    },
    "filename": "back-end/src/main/java/com/example/chatonline/controller/UserController3.java",
    "groupTitle": "好友"
  },
  {
    "type": "get",
    "url": "ShowGroup",
    "title": "显示好友分组",
    "description": "<p>显示好友分组接口</p>",
    "group": "好友",
    "name": "显示好友分组",
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
            "description": "<p>返回相关信息，成功的时候才存在</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "显示好友分组-示例:",
          "content": "HTTP/1.1 200 OK\n\"message\": \"success\",\n\"data\": [\n    {\n        \"groupname\": \"分组一\",\n        \"count_num\": 6\n    },\n    {\n        \"groupname\": \"分组二\",\n        \"count_num\": 6\n    }\n],\n\"code\": 1\n}",
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
          "title": "分组信息为空-示例：",
          "content": " HTTP/1.1 200 OK\n{\n  \"code\":-1,\n  \"message\": \"未能查询到分组信息\",\n  \"data\":null\n}",
          "type": "json"
        }
      ]
    },
    "filename": "back-end/src/main/java/com/example/chatonline/controller/UserController2.java",
    "groupTitle": "好友"
  },
  {
    "type": "post",
    "url": "addfriend",
    "title": "发送添加好友请求",
    "description": "<p>添加好友请求接口</p>",
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
          "content": "HTTP/1.1 200 OK\n{\n  \"code\":1,\n  \"message\": \"success\",\n  \"data\": \"发送成功\"\n}",
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
          "content": " HTTP/1.1 200 OK\n{\n  \"code\":1,\n  \"message\": \"发送失败\",\n  \"data\":null\n}",
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
          "content": "HTTP/1.1 200 OK\n{\n  \"code\":1,\n  \"message\": \"success\",\n  \"data\": [group1,group2...]\n}",
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
          "content": " HTTP/1.1 200 OK\n{\n  \"code\":0,\n  \"message\": \"该用户已在好友列表中\",\n  \"data\":null\n}\n\n HTTP/1.1 200 OK\n{\n  \"code\":-1,\n  \"message\": \"未能查询到分组信息\",\n  \"data\":null\n }",
          "type": "json"
        }
      ]
    },
    "filename": "back-end/src/main/java/com/example/chatonline/controller/UserController.java",
    "groupTitle": "好友"
  },
  {
    "type": "post",
    "url": "handlemessage",
    "title": "处理验证消息",
    "description": "<p>处理验证消息接口</p>",
    "group": "消息",
    "name": "处理验证消息",
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
            "field": "sendId",
            "description": "<p>发送方ID</p>"
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
            "description": "<p>备注</p>"
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
            "description": "<p>返回分组信息 成功的时候才存在</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "添加好友成功-示例:",
          "content": "HTTP/1.1 200 OK\n{\n  \"message\": \"success\",\n  \"data\": \"已添加\",\n  \"code\": 1\n}",
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
          "title": "未能查询到分组信息-示例：",
          "content": " HTTP/1.1 200 OK\n{\n  \"code\":0,\n  \"message\": \"添加失败\",\n  \"data\":null\n}",
          "type": "json"
        }
      ]
    },
    "filename": "back-end/src/main/java/com/example/chatonline/controller/UserController.java",
    "groupTitle": "消息"
  },
  {
    "type": "get",
    "url": "ShowFriendLastMessage",
    "title": "消息列表",
    "description": "<p>显示消息列表接口</p>",
    "group": "消息",
    "name": "显示消息列表",
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
            "description": "<p>返回相关信息，成功的时候才存在</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "消息列表显示成功-示例:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\":1,\n  \"message\": \"success\",\n  \"data\": [\n    {\n        \"messagetxt\": \"\",\n        \"friendId\": \"\",\n        \"nickname\": \"\",\n        \"sendtime\": \"2020-12-03T09:46:01.000+00:00\"\n    }]\n}",
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
          "title": "消息列表为空-示例：",
          "content": " HTTP/1.1 200 OK\n{\n  \"code\":0,\n  \"message\": \"消息列表为空\",\n  \"data\":null\n}",
          "type": "json"
        }
      ]
    },
    "filename": "back-end/src/main/java/com/example/chatonline/controller/UserController2.java",
    "groupTitle": "消息"
  },
  {
    "type": "post",
    "url": "showveritymessage",
    "title": "显示验证消息",
    "description": "<p>显示验证消息接口</p>",
    "group": "消息",
    "name": "显示验证消息",
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
            "description": "<p>返回相关信息，成功的时候才存在</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "有验证消息-示例:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\":1,\n  \"message\": \"success\",\n  \"data\": [\n    {\n        \"sendid\": \"\",\n        \"reciveid\": \"\",\n        \"sendtime\": \"\",\n        \"messagetext\": \"\",\n        \"texttype\": 1 //1表示已同意\n    },\n    {\n        \"sendid\": \"\",\n        \"reciveid\": \"\",\n        \"sendtime\": \"\",\n        \"messagetext\": \"\",\n        \"texttype\": 0 //0表示尚未处理\n    },\n    {\n        \"sendid\": \"\",\n        \"reciveid\": \"\",\n        \"sendtime\": \"\",\n        \"messagetext\": \"\",\n        \"texttype\": -1 //-1表示已拒绝\n    }\n]\n}",
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
          "title": "无验证消息-示例：",
          "content": " HTTP/1.1 200 OK\n{\n  \"code\":0,\n  \"message\": \"暂无验证消息\",\n  \"data\":null\n}",
          "type": "json"
        }
      ]
    },
    "filename": "back-end/src/main/java/com/example/chatonline/controller/UserController.java",
    "groupTitle": "消息"
  },
  {
    "type": "post",
    "url": "showveritymessage",
    "title": "预处理验证消息",
    "description": "<p>预处理验证消息接口</p>",
    "group": "消息",
    "name": "预处理验证消息",
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
            "field": "sendId",
            "description": "<p>发送方ID</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "type",
            "description": "<p>1表示同意、-1表示拒绝</p>"
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
            "description": "<p>返回分组信息 成功的时候才存在</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "type=1-示例:",
          "content": "HTTP/1.1 200 OK\n{\n  \"code\": 1,\n  \"message\": \"success\",\n  \"data\": [\n      {\n          \"groupname\": \"分组一\",\n          \"count_num\": 6\n      }\n  ],\n\n}",
          "type": "json"
        },
        {
          "title": "type=-1-示例:",
          "content": "HTTP/1.1 200 OK\n{\n  \"message\": \"已拒绝\",\n  \"data\": null,\n  \"code\": 0\n}",
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
          "title": "未能查询到分组信息-示例：",
          "content": " HTTP/1.1 200 OK\n{\n  \"code\":-1,\n  \"message\": \"未能查询到分组信息\",\n  \"data\":null\n}",
          "type": "json"
        }
      ]
    },
    "filename": "back-end/src/main/java/com/example/chatonline/controller/UserController.java",
    "groupTitle": "消息"
  },
  {
    "type": "get",
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
          "content": "HTTP/1.1 200 OK\n{\n  \"code\":1,\n  \"message\": \"success\",\n  \"data\": {\n\n  }\n}",
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
          "content": " HTTP/1.1 200 OK\n{\n  \"code\":0,\n  \"message\": \"未查询到该用户\",\n  \"data\":null\n}",
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
          "content": "HTTP/1.1 200 OK\n{\n  \"code\":1,\n  \"message\": \"success\",\n  \"data\": userId\n}",
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
          "content": " HTTP/1.1 200 OK\n{\n  \"code\":0,\n  \"message\": \"注册失败\",\n  \"data\":null\n}",
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
          "content": "HTTP/1.1 200 OK\n{\n  \"code\":1,\n  \"message\": \"success\",\n  \"data\":{}\n}",
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
          "content": " HTTP/1.1 200 OK\n{\n  \"code\":0,\n  \"message\": \"登录失败\",\n  \"data\":null\n}",
          "type": "json"
        }
      ]
    },
    "filename": "back-end/src/main/java/com/example/chatonline/controller/UserController.java",
    "groupTitle": "登录注册"
  }
] });
