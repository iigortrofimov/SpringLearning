<#import "parts/common.ftlh" as common>

<@common.page>
    <h4 class="ml-3">${username}</h4>
    ${message!}
    <form method="post">

        <div class="form-group col-sm-6">
            <label for="exampleInputPassword1"> Password: </label>
            <input type="password" class="form-control" name="password" placeholder="New password">
        </div>

            <div class="form-group col-sm-6">
                <label for="exampleInputPassword1"> E-mail: </label>
                <input type="email" class="form-control" name="email" placeholder="name@email.com" value="${email!''}">
            </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary ml-3">Save</button>
    </form>
</@common.page>
