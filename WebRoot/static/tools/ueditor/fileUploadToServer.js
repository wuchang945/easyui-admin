UE.Editor.prototype._bkGetActionUrl=UE.Editor.prototype.getActionUrl;
UE.Editor.prototype.getActionUrl=function(action){
	if(action=="FileUploadController/uploadImg"){
		return basePath+"/FileUploadController/uploadImg.do";
	}else{
		return this._bkGetActionUrl.call(this,action);
	}
}