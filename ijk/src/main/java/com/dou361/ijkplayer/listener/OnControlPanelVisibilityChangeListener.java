package com.dou361.ijkplayer.listener;
/**
 * ========================================
 * <p>
 * 版 权：dou361.com 版权所有 （C） 2015
 * <p>
 * 作 者：陈冠明
 * <p>
 * 个人网站：http://www.dou361.com
 * <p>
 * 版 本：1.0
 * <p>
 * 创建日期：2016/8/10 15:27
 * <p>
 * 描 述：操作面板显示或者隐藏改变监听
 * <p>
 * <p>
 * 修订历史：
 * <p>
 * ========================================
 */
public interface OnControlPanelVisibilityChangeListener {

    /**true 为显示 false为隐藏*/
    void change(boolean isShowing);
}