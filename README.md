# Rena Interact Accessor
クリックしたエンティティに応じた処理を簡潔に記述するためのライブラリ

## 導入
RenaInteractAccessorは、

1. サーバーにjarを導入する
2. 作成するプラグインにshadeする

の二つの方法を用いて扱うことができます。

### サーバーにプラグインを導入する場合
pluginsディレクトリにRenaInteractAccessorを配置してください。

### お使いのプラグインにshadeする場合
onEnable内から、
```java
RenaInteractAccessor.onEnable(this);
```
を行ってください。

## 使用例
```java
final InteractAccessor accessor = new InteractAccessor(player)
        // 自動的にキャンセルされるまでのtick数
        .expirationTicks(20 * 60)
        // 入力を受け取りお知らせする
        .onResponse(interactedEntity -> Bukkit.broadcastMessage("クリックしたエンティティ : " + input))
        // 時間切れ・ログアウト・サーバー閉鎖でキャンセルされた場合
        .onCancel(() -> player.sendMessage("有効期限切れのためキャンセルされました。"));

// InteractAccessorを登録する。すでに登録されているInteractAccessorがある場合はキャンセルして登録する
RenaInteractAccessor.getInteractAccessorManager().register(accessor);
```