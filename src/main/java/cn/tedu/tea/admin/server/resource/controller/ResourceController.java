package cn.tedu.tea.admin.server.resource.controller;

import cn.tedu.tea.admin.server.common.ex.ServiceException;
import cn.tedu.tea.admin.server.common.web.JsonResult;
import cn.tedu.tea.admin.server.common.web.ServiceCode;
import cn.tedu.tea.admin.server.resource.pojo.vo.UploadResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/resource")
@Validated
@Api(tags = "3. 资源管理模块")
public class ResourceController {

    @Value("${tea-store.upload.host}")
    private String host;

    @Value("${tea-store.upload.root-dir-name}")
    private String rootDirName;

    @Value("${tea-store.upload.base-dir-name}")
    private String baseDirName;

    private String imageDirName = "article-image/";

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");

    @Value("${tea-store.upload.article-image.types}")
    private String imageTypes;

    @Value("${tea-store.upload.article-image.max-size}")
    private Long imageMaxSize;

    public ResourceController() {
        log.info("创建控制器对象：ResourceController");
    }

    @PostMapping("/upload/image")
    @ApiOperation("3.1 图片上传")
    public JsonResult uploadImage(@RequestParam(value = "file") MultipartFile multipartFile) throws IOException {
        Long size = multipartFile.getSize();
        if (size > imageMaxSize * 1024 * 1024) {
            String message = "文件不能大于 5 MB";
            log.debug("message");
            throw new ServiceException(ServiceCode.ERROR_UPLOAD_EXCEED_MAX_SIZE, message);
        }

        String contentType = multipartFile.getContentType();
        if (!imageTypes.contains(contentType)) {
            String message = "请上传" + imageTypes + "文件";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERROR_UPLOAD_INVALID_TYPE, message);
        }

        /* 定义转换后的文件名 */
        String uuidName = UUID.randomUUID().toString();
        String fileName = multipartFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String convertName = new StringBuilder()
                                 .append(uuidName)
                                 .append(suffix)
                                 .toString();

        /* 定义转换后的文件存储目录 */
        File rootDir = new File(rootDirName);
        File baseDir = new File(rootDir, baseDirName);
        File imageDir = new File(baseDir, imageDirName);
        String dateDirName = simpleDateFormat.format(new Date());
        File dateDir = new File(imageDir, dateDirName);
        if (!dateDir.exists()) {
            dateDir.mkdirs();
        }
        File file = new File(dateDir, convertName);


        /* 进行存储操作 */
        multipartFile.transferTo(file);

        /* 构建返回值放入 JsonResult 当中 */
        String url = new StringBuilder()
                .append(host)
                .append(rootDirName)
                .append(baseDirName)
                .append(imageDirName)
                .append(dateDirName)
                .append(convertName)
                .toString();

        UploadResult uploadResult = new UploadResult();
        uploadResult.setFileName(convertName)
                .setFileSize(size)
                .setContentType(contentType)
                .setUrl(url);

        return JsonResult.ok(uploadResult);
    }
}
