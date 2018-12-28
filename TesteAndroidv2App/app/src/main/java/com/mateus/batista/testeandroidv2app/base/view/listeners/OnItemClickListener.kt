package com.mateus.batista.testeandroidv2app.base.view.listeners

interface OnItemClickListener<T> {

    fun onItemClick(item: T, position: Int)
}