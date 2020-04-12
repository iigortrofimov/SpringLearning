<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group col-sm-6">
            <label for="exampleInputEmail1"> User Name: </label>
            <input type="text" name="username" class="form-control" id="exampleInputEmail1" placeholder="Enter User Name "/>
        </div>
        <div class="form-group col-sm-6">
            <label for="exampleInputPassword1"> Password: </label>
            <input type="password" class="form-control" name="password" placeholder="Password">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#if !isRegisterForm>
        <a class="ml-3" href="/registration">Add new user</a>
        </#if>
        <button type="submit" class="btn btn-primary ml-3"><#if isRegisterForm > Create <#else> Log In </#if></button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary" type="submit">Sign Out</button>
    </form>
</#macro>