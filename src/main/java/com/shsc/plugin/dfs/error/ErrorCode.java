package com.shsc.plugin.dfs.error;

/**
 * @author fangxs
 * @className ErrorCode
 * @date 2019/6/27 14:33
 * @description   错误信息
 **/
public enum  ErrorCode {
    /**
     * 文件路径为空
     */
    FILE_PATH_ISNULL("error.fastdfs.file_path_isnull", "文件路径为空"),
    /**
     * 文件为空
     */
    FILE_ISNULL("error.fastdfs.file_isnull", "文件为空"),
    /**
     * 文件上传失败
     */
    FILE_UPLOAD_FAILED("error.fastdfs.file_upload_failed", "文件上传失败"),

    /**
     * 文件不存在
     */
    FILE_NOT_EXIST("error.fastdfs.file_not_exist", "文件不存在"),

    /**
     * 文件下载失败
     */
    FILE_DOWNLOAD_FAILED("error.fastdfs.file_download_failed", "文件下载失败"),

    /**
     * 删除文件失败
     */
    FILE_DELETE_FAILED("error.fastdfs.file_delete_failed", "删除文件失败"),

    /**
     * 文件服务器连接失败
     */
    FILE_SERVER_CONNECTION_FAILED("error.fastdfs.file_server_connection_failed", "文件服务器连接失败"),

    /**
     * 文件超过大小
     */
    FILE_OUT_SIZE("error.fastdfs.file_server_connection_failed", "文件超过大小"),

    /**
     * 图片类型错误
     */
    FILE_TYPE_ERROR_IMAGE("error.file.type.image", "图片类型错误"),

    /**
     * 文档类型错误
     */
    FILE_TYPE_ERROR_DOC("error.file.type.doc", "文档类型错误"),

    /**
     * 音频类型错误
     */
    FILE_TYPE_ERROR_VIDEO("error.file.type.video", "音频类型错误"),

    /**
     * 压缩文件类型错误
     */
    FILE_TYPE_ERROR_COMPRESS("error.file.type.compress", "压缩文件类型错误");


    public String CODE;

    public String MESSAGE;

    ErrorCode(String CODE, String MESSAGE){
        this.CODE = CODE;
        this.MESSAGE = MESSAGE;
    }
}
