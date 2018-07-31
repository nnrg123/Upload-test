# -*- coding: utf-8 -*-
# flake8: noqa
from qiniu import Auth, put_file, etag
import qiniu.config
access_key = 'wb3L0e4GBOf_Kq4IVS8y9Csq9fC3u8UDmqb2S-pj'
secret_key = 'XWo3jAwdW7ETQnJsnWTIRedrmEc6Au-8jt2Xj9KV'
q = Auth(access_key, secret_key)
bucket_name = 'nnrg'
key = 'test1.pdf'
#上传文件到七牛后， 七牛将文件名和文件大小回调给业务服务器。
policy={
 'callbackUrl':'http://pcg9u84dk.bkt.clouddn.com/callback.php',
 'callbackBody':'filename=$(fname)&filesize=$(fsize)'
 }
token = q.upload_token(nnrg, key, 3600, policy)
localfile = './sync/bbb.jpg'
ret, info = put_file(token, key, localfile)
print(info)
assert ret['key'] == key
assert ret['hash'] == etag(localfile)