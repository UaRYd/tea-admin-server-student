**第1次作业（2023.06.13）**

- 创建`content_`系列数据表对应的实体类
  - 各实体类均需要配置`@TableName`、`@TableId`、`@TableField`
- 创建`content_`系列数据表对应的标准VO类和列表项VO类
- 创建`content_`系列数据表对应的Mapper接口，均继承自`BaseMapper`，泛型为对应的实体类型
- 创建各Mapper接口对应的XML文件
- 实现每种数据的“根据ID查询详情”（返回值类型为标准VO类型）功能
- 创建各Mapper接口对应的测试类，并在测试类中测试以上“根据ID查询详情”功能

提交时间：明天早上9点之前。



**第2次作业（2023.06.14）**

- 创建`content_`系列数据表的Repository接口与实现类
- 在各Repository中均实现“添加数据”的功能
- 对各Repository的“添加数据”功能进行测试
- 创建`content_category`、`content_article`、`content_comment`对应的Service接口与实现类
- 在各Service中均实现“添加数据”的功能，暂时不需要设计业务规则
- 对各Service的“添加数据”功能进行测试

提交时间：明天早上9点之前。



**第3次作业（2023.06.15）**

- 创建VUE脚本架项目，项目名称参考：`tea-admin-client`
- 在项目中安装Element UI、axios，并配置
- 修改项目的端口号为：`9000`
- 设计登录页，访问URL为：`/login`
  - 页面内容可参考此前的VUE脚手架案例
- 设计后台管理页框架，是上下结构的，下半部分的内部是左右结构的
  - 顶栏中添加标题文字：`学茶商城后台管理平台`
  - 左侧边栏添加菜单，菜单项暂不要求
  - 右侧主体使用`<router-view/>`
- 创建“新增标签类别”页面，将显示在以上框架页的`<router-view/>`，访问URL为：`/admin/content/tag/type/add-new`
  - 页面内容暂不要求

提交时间：明天早上9点之前。

注意：提交时，删除压缩包中的`node_modules`文件夹。



**第4次作业（2023.06.17）**

- 实现“添加标签”功能
  - DAO层无需开发新功能
  - Service层添加`void addNew(TagAddNewParam tagAddNewParam)`方法，并实现，业务规则：名称必须唯一
    - 相对标签类别，标签数据需要增加typeId（对应数据表中的parent_id）属性，表示此标签归属哪个标签类别
  - Controller层处理此请求，URL设计为`/content/tags/add-new`
  - 各层均需要测试
  - 前端页面不要求完成
- 实现“显示标签列表”功能
  - DAO层中：
    - Mapper实现`List<TagListItemVO> list()`查询
    - Repository实现`PageData<TagListItemVO> list(Integer pageNum, Integer pageSize)`查询
  - Service层添加方法并实现：
    - `PageData<TagListItemVO> list(Integer pageNum)`
    - `PageData<TagListItemVO> list(Integer pageNum, Integer pageSize)`
    - 以上方法均无业务规则
  - Controller层处理此请求，URL设计为`/content/tags`
    - 在方法上配置为`@GetMapping("")`
  - 各层均需要测试
  - 要求完成前端页面的数据显示，各管理功能（例如编辑、删除等）可不实现

提交时间：下周一早上9点之前。



**第5次作业（2023.06.24）**

- 实现“用户管理”的相关功能，包括后端与前端的开发
  - 添加用户
  - 根据ID删除用户
  - 启用用户
  - 禁用用户
  - 根据ID查询用户
  - 查询用户列表

提交时间：6月25日早上9点之前。



**第6次作业（2023.06.30）**

在`front-server`项目中实现：

- 查询类别列表
  - 仅查询一级类别（parent_id=0），启用的（enable=1）、显示的（is_display=1）类别
  - 查询的数据项包括：id、名称（name）
  - 无业务规则，不需要分页
  - 无权限限制
  - 通过API文档可以测试访问

提交时间：7月1日早上9点之前。



**第7次作业（2023.07.01）**

在`font-server`项目中实现：

- 【在课上完成代码的基础上继续完成】缓存文章列表，要求：
  - 通过API文档查询时（仍支持分页查询），返回的是缓存的文章列表
  - 计划任务每间隔一段时间自动重建文章列表的缓存数据
- 实现“根据ID查询文章详情”，暂不缓存
  - 通过API文档可查询即可
  - 可以参考`admin-server`中的实现
  - 注意：查询结果中不要体现敏感数据，例如：display_state、check_state等
- 发表文章的评论
  - 业务细节均可暂不实现，但应通过 TODO 标记后续应该补充哪些业务细节
  - 参考`admin-server`项目中的发布文章，业务方法的参数应该有：当事人、IP、前端提交的参数
  - 步步为营，及时测试
  - 通过API文档可以访问
- 实现“根据文章ID查询评论列表”，暂不缓存
  - 通过API文档可查询即可

提交时间：7月3日早上9点之前。
