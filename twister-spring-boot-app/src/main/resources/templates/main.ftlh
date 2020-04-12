<#import "parts/common.ftlh" as common>

<@common.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter!}" placeholder="Search by tag">
                <button type="submit" class="btn btn-primary ml-3">Search</button>
            </form>
        </div>
    </div>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new Message
    </a>
    <div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
    <form method="post" enctype="multipart/form-data">
        <div class="form-group">
            <input type="text" class="form-control" name="text" placeholder="Enter the text: "/>
        </div>

        <div class="form-group">
            <input type="text" class="form-control" name="tag" placeholder="Tag:"/>
        </div>
        <div class="form-group">
            <div class="custom-file">
                <input type="file" name="file" id="customFile">
                <label class="custom-file-label" for="customFile">Choose file</label>
            </div>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Add</button>
    </form>
    </div>
    </div>
    <div class="card-columns">
        <#list messages as mess>
            <div class="card my-3">
                <#if mess.filename??>
                    <img src="/img/${mess.filename}" class="card-img-top">
                </#if>
                <div class="m-2">
                    <span>${mess.text}</span>
                    <i>${mess.tag}</i>
                </div>
                <div class="card-footer text-muted">
                    ${mess.authorName}
                </div>
            </div>
        <#else >
            No messages!
        </#list>
    </div>
</@common.page>