package com.rotaeskisehir.Model

//Bu alanda reddit desteği ile yorum ayrıştıcıları kullanılarak mekanların altında yer alan yorum yapma kısmı tanımlanmıştır.
class Yorumlar {
    override fun loadInfo(permalink: String) {
        compositeSubscription.add(
                postStore.getPost(permalink)
                        .subscribeOn(Schedulers.io())
                        .flatMapIterable({ redditResponses -> redditResponses })
                        .flatMap(RedditDataParser())
                        .filter({ redditContent -> redditContent.kind == “t1” &&    redditContent.redditContentData != null})
                        .map(CommentParser())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : Observer<Comment> {
                            override fun onCompleted() {
                                Log.e(“tag”, “completed”)
                            }
                            override fun onError(e: Throwable) {
                                Log.e(“tag”, “e “ + e.printStackTrace())
                            }
                            override fun onNext(comment: Comment) {
                                view.showComment(comment)
                            }
                        }))
    }