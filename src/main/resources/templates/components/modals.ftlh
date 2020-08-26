<#macro infoModal>
    <div id="infoModal" class="modal fade" tabindex="-1" data-keyboard="false" data-backdrop="static">
        <div class="modal-dialog modal modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 id="resultRegistrationModalText" class="modal-title">Modal title</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    </button>
                </div>
                <div class="modal-footer">
                    <button id="resultRegistrationModalButton" type="button" class="btn btn-success"
                            data-dismiss="modal">Хорошо
                    </button>
                </div>
            </div>
        </div>
    </div>
</#macro>

<#macro deleteStudentModal>
    <div id="deleteStudentModal" class="modal fade" tabindex="-1" data-keyboard="false" data-backdrop="static">
        <div class="modal-dialog modal modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Вы действительно хотите удалить студента?</h5>
                </div>
                <div class="modal-footer">
                    <button id="deleteStudentModalButton" type="button" class="btn btn-success">Да
                    </button>
                    <button type="button" class="btn btn-danger"
                            data-dismiss="modal">Нет
                    </button>
                </div>
            </div>
        </div>
    </div>
</#macro>

<#macro adminModal>
    <#import "forms.ftlh" as forms>
    <div id="adminStudentModal" class="modal fade" tabindex="-1" data-keyboard="false" data-backdrop="static">
        <div class="modal-dialog modal-xl modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 id="adminStudentModalLabel" class="modal-title">Label</h5>
                </div>
                <div class="modal-body">
                    <@forms.adminForm/>
                </div>
                <div class="modal-footer">
                    <button id="adminStudentModalDeleteButton" type="button" class="btn btn-outline-danger"
                            onclick="deleteAdminStudentForm()">Удалить
                    </button>
                    <button id="adminStudentModalSaveButton" type="button" class="btn btn-success"
                            onclick="submitAdminStudentForm()">Сохранить
                    </button>
                    <button id="adminStudentModalCloseButton" type="button" class="btn btn-primary"
                            data-dismiss="modal">Закрыть
                    </button>
                </div>
            </div>
        </div>
    </div>
</#macro>