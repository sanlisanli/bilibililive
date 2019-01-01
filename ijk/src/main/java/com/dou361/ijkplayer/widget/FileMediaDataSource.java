/*
 * Copyright (C) 2015 Zhang Rui <bbcallen@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dou361.ijkplayer.widget;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import tv.danmaku.ijk.media.player.misc.IMediaDataSource;
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
 * 创建日期：2016/8/10 15:29
 * <p>
 * 描 述：本地文件媒体数据源对象
 * <p>
 * <p>
 * 修订历史：
 * <p>
 * ========================================
 */
public class FileMediaDataSource implements IMediaDataSource {
    private RandomAccessFile mFile;
    private long mFileSize;

    public FileMediaDataSource(File file) throws IOException {
        mFile = new RandomAccessFile(file, "r");
        mFileSize = mFile.length();
    }

    @Override
    public int readAt(long position, byte[] buffer, int offset, int size) throws IOException {
        if (mFile.getFilePointer() != position)
            mFile.seek(position);

        if (size == 0)
            return 0;

        return mFile.read(buffer, 0, size);
    }

    @Override
    public long getSize() throws IOException {
        return mFileSize;
    }

    @Override
    public void close() throws IOException {
        mFileSize = 0;
        mFile.close();
        mFile = null;
    }
}
