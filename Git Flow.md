# Git Flow

### * 在 commit 提交之前一定要记得

```bash
$ git pull // 拉取最新远程仓库
```

- ### 在书写代码之前

  ```bash
  $ git pull // 拉取最新的仓库信息
  
  $ git checkout -b [your branch] // 创建并切换到你自己的分支
  如我创建一个 test分支
  $ git checkout -b feature/test
  
  $ git branch // 可以查看你当前的分支
  ```

  #### 切记一定不要直接提交到 master 分支上

  - ### 分支的命名规范

    - 如果创建新功能： feature/功能名
    - 如果修复 bug ：fix/bug名

- ### 提交

  ``` bash
  $ git add . // 提交所有修改文件，！！！！！！切记一定要观察是否是你需要提交的文件
  // 如果需要提交指定文件
  $ git add filename
  
  $ git commit -m "some message" // 提交到 git 暂存区, 一定要写提交有用的信息, 来描述该提交, 推荐 git emjio,当然也可以不用
  // 像这样
  $ git commit -m ":sparkles: 实现聊天功能"
  
  $ git push // 提交到 github 远程仓库
  
  !!!!!在提交之前一定要记得 pull
  ```

  ![image-20201202224529282](C:\Users\25118\AppData\Roaming\Typora\typora-user-images\image-20201202224529282.png)

- ### 其他

  ```bash
  $ git checkout . // 清除你本地所有修改的代码, 必要时刻用的上
  ```

  