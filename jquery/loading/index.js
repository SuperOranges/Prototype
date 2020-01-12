var Post = function(){
    var count = 0;
    return {
        PostA:function(name){
            if(count ===0){
                console.log("打开loading框");
            }
            count++;
            console.log("count"+name+":"+count);
            console.log("发起请求！"+name);
            var time = Math.ceil(Math.random()*10)*1000;
            console.log("耗时"+time);
            setTimeout(function(){
                console.log("请求结束了！"+name)    
                count--;
                console.log("count"+name+":"+count);
                if(count === 0){
                    console.log("关闭loading框");
                }
            },time);
        },
        PostB:function(){
            var data={};
            return {
                getData:function(name){
                    if(count ===0){
                        console.log("打开loading框");
                    }
                    count++;
                    console.log("count"+name+":"+count);
                    console.log("发起请求！"+name);
                    var time = Math.ceil(Math.random()*10)*1000;
                    console.log("耗时B"+time);
        
                    setTimeout(function(){
                        console.log("请求结束了！"+name)    
                        count--;
                        data= {
                            s:"aa"
                        };
                        console.log(data);
                        console.log("count"+name+":"+count);
                        if(count === 0){
                            console.log("关闭loading框");
                        }
                    },time);
                    
                }
            }
        }
    }
}
var post = new Post();
post.PostA("a12");
post.PostA("b34");
post.PostB().getData("cache1234");
post.PostA("c56");
