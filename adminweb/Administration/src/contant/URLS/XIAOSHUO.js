/**
 * 小说
 * created by bo.peng
 * email:spion@qq.com
 */
const URL = require("./index").URL+"/xiaoshuo";
module.exports={
    //更新分类
    UPDATE_CLASSIFY: URL + "/updateClassify",
    //文章列表
    CHAPTER_LIST: URL + "/chapterList",
    //小说列表
    LIST : URL + "/list",
    //分类列表
    CLASSIFY_LIST : URL + "/classifyList",
};
