<!-- TOC -->

- [ava-alluxio](#ava-alluxio)
  - [前置文档](#%E5%89%8D%E7%BD%AE%E6%96%87%E6%A1%A3)
  - [本地开发](#%E6%9C%AC%E5%9C%B0%E5%BC%80%E5%8F%91)
  - [部署](#%E9%83%A8%E7%BD%B2)
    - [前置条件](#%E5%89%8D%E7%BD%AE%E6%9D%A1%E4%BB%B6)
  - [工具](#%E5%B7%A5%E5%85%B7)
    - [生成 alluxio 包](#%E7%94%9F%E6%88%90-alluxio-%E5%8C%85)
    - [生成 alluxio 镜像](#%E7%94%9F%E6%88%90-alluxio-%E9%95%9C%E5%83%8F)
    - [alluxio 批量加载工具 avio](#alluxio-%E6%89%B9%E9%87%8F%E5%8A%A0%E8%BD%BD%E5%B7%A5%E5%85%B7-avio)
  - [帮助](#%E5%B8%AE%E5%8A%A9)

<!-- /TOC -->

# ava-alluxio

此代码库主要包含 ava 组提供的 alluxio 服务的本地开发和部署脚本等代码。

## 前置文档

- [`alluxio`](https://www.alluxio.org/docs/1.7/en/index.html)
- [`fuse`](https://github.com/libfuse/libfuse/tree/master/doc)

## 本地开发

@TODO


5. 依照[下述方式](#%E7%94%9F%E6%88%90-alluxio-%E5%8C%85)生成 alluxio 包，创建 Jira issue 给 Kirk 组相关同学帮忙更新 k8s 集群中各节点上的 alluxio worker 实例。

## 工具

### 生成 alluxio 包

```shell
./tools/scripts/build-alluxio.sh
cd .tmp/alluxio
hash=`git rev-parse --short=7 HEAD` && tar zcvf alluxio-1.7.2-${hash}.tar.gz ./alluxio-1.7.2-SNAPSHOT
```

如需要使用本地尚未合并到 [alluxio](github.com/qiniu-ava/alluxio) 中的代码，则可以在执行 build-alluxio.sh 脚本时指定 --local-alluxio 参数，如

```shell
./tools/scrips/build-alluxio.sh --local-alluxio=$HOME/qbox/alluxio
```

关于 ./tools/scrips/build-alluxio.sh 脚本更多的使用细则，请查看脚本中的 usage 说明。

### 生成 alluxio 镜像

1. 在本地登录 reg-xs.qiniu.io registry

``` shell
docker login reg-xs.qiniu.io -u altab -p <atlab_password>
```

2. 执行如下脚本生成本地 alluxio 镜像

```shell
./tools/scripts/build-alluxio.sh
```

3. 执行如下脚本推送 alluxio 镜像

```shell
docker tag alluxio reg.xs.qiniu.io/atlab/alluxio
docker push reg.xs.qiniu.io/atlab/alluxio
```

### alluxio 批量加载工具 avio

avio 相关详情介绍请参阅 [CF文档](https://cf.qiniu.io/pages/viewpage.action?pageId=81986327)


#### 发布 avio 新版本

请先确认已在本地安装好 qrsctl 且以 ava-test 账号登录，并确定要发布的版本号(可以在 tools/golang/src/qiniu.com/app/avio/main.go 中改动)。

``` shell
make tools-golang-deploy
```

发布后可以在 `http://devtools.dl.atlab.ai/ava/cli/avio` 下载，或者 `http://devtools.dl.atlab.ai/ava/cli/avio/<version>/avio-linux` 下载验证指定版本

## 帮助

关于 alluxio 部署过程中的一些注意问题，请查看文档 [Q&A](https://github.com/qiniu-ava/ava-alluxio/blob/develop/questions.md)。
