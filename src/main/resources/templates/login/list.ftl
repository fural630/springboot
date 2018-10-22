<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>layout 后台大布局 - Layui</title>
<link rel="stylesheet" href="lib/layui/css/layui.css">
</head>
<body>
	<div class="layui-collapse">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">案件基本信息</h2>
            <div class="layui-colla-content layui-show">
                <div class="layui-form-item">
                    <input type="hidden" name="ajbh"/>
                    <label class="layui-form-label">案件名称<i class="star">*</i></label>
                    <div class="layui-input-inline" style="width: 620px;">
                        <input name="ajmc" id="zdmc" class="layui-input" type="text" lay-verify="required">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">案件性质<i class="star">*</i></label>
                    <div class="layui-input-inline" style="width: 310px;">
                        <select id="ajdl" name="ajdl" lay-filter="dl">
                        </select>
                    </div>
                    <div class="layui-input-inline" style="width: 640px;">
                        <select id="ajxz" name="ajxz" lay-filter="ajxz"
                                lay-verify="required">
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">违反规定<i class="star">*</i></label>
                    <div class="layui-input-inline" style="width:500px;">
                        <input name="wfgd" class="layui-input" lay-verify="required" type="text" disabled/>
                    </div>
                    <label class="layui-form-label">处罚依据<i class="star">*</i></label>
                    <div class="layui-input-inline" style="width:340px;">
                        <select name="cfyj" lay-search lay-filter="cfyj" lay-verify="required"></select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">案件来源<i class="star">*</i></label>
                    <div class="layui-input-inline">
                        <select name="ajly" lay-search lay-verify="required"></select>
                    </div>
                    <label class="layui-form-label">受案时间<i class="star">*</i></label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" name="sasj" id="sasj" lay-verify="required"/>
                    </div>
                    <label class="layui-form-label">受案机关<i class="star">*</i></label>
                    <div class="layui-input-inline">
                        <input name="sajgmc" class="layui-input" type="text" disabled lay-verify="required">
                        <input name="sajg" class="layui-input" type="hidden">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">主办人<i class="star">*</i></label>
                    <div class="layui-input-inline">
                        <input name="zbr" class="layui-input" type="text" disabled lay-verify="required">
                        <input name="zbrbh" class="layui-input" type="hidden">
                    </div>
                    <label class="layui-form-label" style="width: 112px;">协办人</label>
                    <div class="layui-input-inline">
                        <select name="xbr" lay-ignore multiple="multiple" placeholder="输入可搜索">
                        </select>
                        <!--
                        <input name="xbr" id="xbr" class="layui-input" type="text">
                        <input name="xbrbh" id="xbrbh" class="layui-input" type="hidden" >
                         -->
                    </div>
                    <label class="layui-form-label" id="wtdwTitle">委托单位</label>
                    <div class="layui-input-inline">
                        <select name="wtdw" lay-search lay-filter="wtdw" disabled
                                class="layui-input layui-disabled"></select>
                    </div>
                    <input type="checkbox" value="1" name="isWtba" title="委托办案" lay-skin="primary" lay-filter="isWtba">
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">地块</label>
                    <div class="layui-input-inline">
                        <input name="dk" class="layui-input" type="text" placeholder="请输入地块">
                    </div>
                    <label class="layui-form-label">小班</label>
                    <div class="layui-input-inline">
                        <input name="xb" class="layui-input" type="text" placeholder="请输入小班">
                    </div>
                    <label class="layui-form-label">林班</label>
                    <div class="layui-input-inline">
                        <input name="lb" class="layui-input" type="text" placeholder="请输入林班">
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <div class="layui-row layui-col-space10">
                        <div class="layui-col-md6">
                            <label class="layui-form-label">简要案情<i class="star">*</i></label>
                            <div class="layui-input-block">
									<textarea placeholder="（时间、地点、人员、事实经过等）" name="jyaq"
                                              class="layui-textarea" lay-verify="required"
                                              style="height: 250px;"></textarea>
                            </div>
                        </div>
                        <div class="layui-col-md6">
                            <div class="layui-row">
                                <label class="layui-form-label">处罚标准<i class="star">*</i></label>
                                <div class="layui-input-block">
                                    <textarea class="layui-textarea" name="cfbz" lay-verify="required"></textarea>
                                </div>
                            </div>
                            <div class="layui-row" style="margin-top:10px;">
                                <label class="layui-form-label">出警线索</label>
                                <div class="layui-input-block">
                                    <textarea class="layui-textarea" name="cjxs"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">损失</label>
                    <div class="layui-input-inline">
                        <input name="ss" class="layui-input" type="text"/>
                    </div>
                    <label class="layui-form-label" style="width: 112px;">补救措施</label>
                    <div class="layui-input-inline">
                        <input name="bjcs" class="layui-input" type="text"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>