<#macro login path isRegisterForm>
    <form action="${path}" method="post">

        <div class="form-group col-sm-6">
            <label for="exampleInputEmail1"> User Name: </label>
            <input type="text" name="username" value="<#if user??>${user.username}</#if>" class="form-control ${(usernameError??)?string('is-invalid', '')}" id="exampleInputEmail1" placeholder="Enter User Name "/>
            <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                </div>
            </#if>
        </div>

        <div class="form-group col-sm-6">
            <label for="exampleInputPassword1"> Password: </label>
            <input type="password" class="form-control ${(passwordError??)?string('is-invalid', '')}" name="password" placeholder="Password">
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
        </div>

        <#if isRegisterForm>
            <div class="form-group col-sm-6">
                <label for="exampleInputPassword1"> Confirm password: </label>
                <input type="password" class="form-control ${(password2Error??)?string('is-invalid', '')}" name="password2" placeholder="Retype password">
                <#if password2Error??>
                    <div class="invalid-feedback">
                        ${password2Error}
                    </div>
                </#if>
            </div>
            <div class="form-group col-sm-6">
                <label for="exampleInputPassword1"> E-mail: </label>
                <input type="email" value="<#if user??>${user.email}</#if>" class="form-control ${(emailError??)?string('is-invalid', '')}" name="email" placeholder="name@email.com">
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
                </#if>
            </div>
        </#if>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>

        <#if !isRegisterForm>
            <a class="texts ml-3" href="/registration">Add new user</a>
        </#if>
        <button type="submit" class="btn btn-primary ml-3"><#if isRegisterForm > Create <#else> Log In </#if> </button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary" type="submit">Sign Out</button>
    </form>
</#macro>