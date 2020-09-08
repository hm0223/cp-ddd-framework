# cp-ddd-example
以订单中台为例，展示如何使用`cp-ddd`框架组织自己的业务代码。

本案例，一个[订单中台代码库](order-center-cp) + 两个业务前台代码库：[KA业务前台](order-center-bp-ka)和[ISV业务前台](order-center-bp-isv)

`oc` stands for Order Center.

## [order-center-cp](order-center-cp)

订单中心的中台，通过[spec jar](order-center-cp/cp-oc-spec)为业务前台赋能并提供扩展机制。

## [order-center-pattern](order-center-pattern)

订单中心的中台本身的个性化业务。

## 订单中心的多个业务前台

#### [order-center-bp-ka](order-center-bp-ka)

订单中心的业务前台：KA(Key Account)，关键客户的个性化业务通过扩展点的实现完成。

#### [order-center-bp-isv](order-center-bp-isv)

订单中心的业务前台：ISV，独立软件开发商的个性化业务通过扩展点的实现完成。

## 依赖关系

![](/doc/assets/img/ddd-depgraph.png)