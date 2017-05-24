# 生命周期

按顺序执行以下操作

## 单个 Acitity

首次打开：onCreate -> onResume

按 HOME 键：onPause -> onStop

双击 HOME 键：onRestart -> onResume

按菜单键：onPause -> onStop

选中当前 APP：onRestart -> onResume

按返回键：onPause -> onStop -> onDestory

## 多个 Activity

假设有两个 Activity

* MainActivity
* NextActivity

当从 `MainActivity` 跳到 `NextActivity`

    MainActivty onPause -> NextActivity onCreate -> NextActivity onResume -> MainActivty onStop


当从 `NextActivity` 返回 `MainActivty`

    NextActivity onPause -> MainActivity onRestart -> MainActivity onResume -> NextActivity onStop -> NextActivity onDestroy