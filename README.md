# StatePage
以状态模式设计的自定义控件,显示不同的状态

## 依赖
在项目的的build.gradle中添加jitpack的仓库
```java
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

在Module中添加依赖
```java
dependencies {
		compile "com.github.jestar719:StatePage:1.0.4"
	}
```

## 创建

* 手动创建

```java
mStatePageView = new StatePageView(context);
((ViewGroup) getWindow().getDecorView()).addView(mStatePageView);

```

* 在布局中设置

```xml
<cn.jestar.statepage.StatePageView
       android:id="@+id/state_page"
       android:layout_width="match_parent"
       android:layout_height="match_parent"
       app:state_error="@layout/layout_state_error"
       app:state_loading="@layout/layout_default_state_loading"
       />

```


## 初始化
1. 添加`IState`的实现
2. 调用StatePageView的`initView()`方法初始化

```java
      View.OnClickListener listener = new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              mPresent.retry();
          }
      };
      mStatePageView.addState(new ErrorState(listener))
              .addState(new NoNetState(listener));
      mStatePageView.initView();
```

## 使用

调用`setState(int state)`方法来切换.在IState中定义几个默认的状态,也可以自定义状态

```java
mStatePageView.setState(IState.STATE_DEFAULT);
mStatePageView.setState(YOUR_CUSTOM_STATE);
```

## IState的创建

* 实现`Istate`接口
* 继承`AbsIstateImpl`类,可以在`handleView`中对布局进行处理,如添加按键监听等
* 继承`DefaultStateImpl`类,如果不想对布局做处理可以直接继承这个类,在构造中传入状态及布局
