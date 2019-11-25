
//设置按钮的属性为启用
//$("#button2").attr('disabled',false);

//设置一个get方法来获取值
var getRadiosValue = function(){
    var checkedValue = $('input[name="radiosGroup1"]:checked').val();
    console.log(checkedValue);
}

// var setButtonAvaliable = function(id){
//     $("#"+id).attr('disabled',false);
// }


//注册监听事件
$('input[name="radiosGroup1"]').on('change',function(){
    //先把所有的按钮设置为禁用
    $('button[type="button"]').attr("disabled",true);
   //console.log($(this).val());
   //然后再把当前选中的radio的父节点下的button节点选中为启用（即radio的兄弟选择器）
   $(this).parent().children("button").attr('disabled',false);
});

//获取值
//getRadiosValue();

//按钮监听事件
$('button[type="button"]').on('click',function(){
    getRadiosValue();
})


