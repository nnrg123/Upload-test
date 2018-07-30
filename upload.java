public class Upload{
    public static void main(String[] args){
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "wb3L0e4GBOf_Kq4IVS8y9Csq9fC3u8UDmqb2S-pj";
        String secretKey = "XWo3jAwdW7ETQnJsnWTIRedrmEc6Au-8jt2Xj9KV";
        String bucket = "nnrg";
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "/test.JPG";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }
}
