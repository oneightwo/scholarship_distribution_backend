<#macro adminForm>
    <form id="adminStudentForm" name="adminStudentForm" class="needs-validation mb-0" novalidate>
        <input id="id" type="text" class="form-control" aria-label="id" hidden>
        <input id="data_registration" type="text" class="form-control" aria-label="data_registration" hidden>
        <#--        <input id="is_valid" type="text" class="form-control" aria-label="is_valid" hidden>-->
        <div class="input-group mb-2">
            <div class="input-group-prepend">
                <span class="input-group-text">Фамилия</span>
            </div>
            <input id="surname" type="text" class="form-control" aria-label="Фамилия" required>
        </div>
        <div class="input-group mb-2">
            <div class="input-group-prepend">
                <span class="input-group-text">Имя</span>
            </div>
            <input id="name" type="text" class="form-control" aria-label="Имя" required>
        </div>
        <div class="input-group mb-2">
            <div class="input-group-prepend">
                <span class="input-group-text">Отчество</span>
            </div>
            <input id="patronymic" type="text" class="form-control" aria-label="Отчество">
        </div>
        <div class="input-group mb-2">
            <div class="input-group-prepend">
                <span class="input-group-text">Университет</span>
            </div>
            <select id="university_id" class="custom-select" aria-label="Университет" required></select>
        </div>
        <div class="input-group mb-2">
            <div class="input-group-prepend">
                <span class="input-group-text">Курс</span>
            </div>
            <select id="course_id" class="custom-select" aria-label="Курс" required></select>
        </div>
        <div class="input-group mb-2">
            <div class="input-group-prepend">
                <span class="input-group-text">Факультет</span>
            </div>
            <input id="faculty" type="text" class="form-control" aria-label="Факультет" required>
        </div>
        <div class="input-group mb-2">
            <div class="input-group-prepend">
                <span class="input-group-text">Email</span>
            </div>
            <input id="email" type="text" class="form-control" aria-label="Email" required>
        </div>
        <div class="input-group mb-2">
            <div class="input-group-prepend">
                <span class="input-group-text">Номер телефона</span>
            </div>
            <input id="phone" type="text" class="form-control" aria-label="Номер телефона">
        </div>
        <div class="input-group mb-2">
            <div class="input-group-prepend">
                <span class="input-group-text">Направление</span>
            </div>
            <select id="science_direction_id" class="custom-select" aria-label="Направление" required></select>
        </div>
        <div class="input-group mb-2">
            <div class="input-group-prepend">
                <span class="input-group-text">Тема</span>
            </div>
            <textarea type="text" id="topic" class="form-control" aria-label="Тема" required></textarea>
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text">Критерии</span>
            </div>
            <input type="text" class="form-control" id="c1" required
                   onchange="changeCriteriaHandler(this)">
            <input type="text" class="form-control" id="c2" required
                   onchange="changeCriteriaHandler(this)">
            <input type="text" class="form-control" id="c3" required
                   onchange="changeCriteriaHandler(this)">
            <input type="text" class="form-control" id="c4" required
                   onchange="changeCriteriaHandler(this)">
            <input type="text" class="form-control" id="c5" required
                   onchange="changeCriteriaHandler(this)">
            <input type="text" class="form-control" id="c6" required
                   onchange="changeCriteriaHandler(this)">
            <input type="text" class="form-control" id="c7" required
                   onchange="changeCriteriaHandler(this)">
            <input type="text" class="form-control" id="c8" required
                   onchange="changeCriteriaHandler(this)">
            <input type="text" class="form-control" id="c9" required
                   onchange="changeCriteriaHandler(this)">
            <input type="text" class="form-control" id="c10" required
                   onchange="changeCriteriaHandler(this)">
            <input type="text" class="form-control" id="c11" required
                   onchange="changeCriteriaHandler(this)">
            <input type="text" class="form-control" id="c12" required
                   onchange="changeCriteriaHandler(this)">
            <input type="text" class="form-control" id="c13" required
                   onchange="changeCriteriaHandler(this)">
            <input type="text" class="form-control" id="c14" required
                   onchange="changeCriteriaHandler(this)">
            <input type="text" class="form-control" id="c15" required
                   onchange="changeCriteriaHandler(this)">
        </div>
        <div class="custom-control custom-checkbox">
            <input id="is_valid" type="checkbox" class="custom-control-input" aria-label="is_valid">
            <label class="custom-control-label" for="is_valid">Проверен</label>
        </div>
    </form>
</#macro>

<#macro registrationForm>
    <div class="card">
        <h5 class="card-header">Регистрация</h5>
        <div class="card-body">
            <form id="studentForm" name="studentForm" class="needs-validation" novalidate>
                <div class="form-row mb-3">
                    <div class="col-4">
                        <label for="surname">Фамилия</label>
                        <input id="surname" type="text" class="form-control" required>
                        <div class="invalid-tooltip">
                            Заполните поле
                        </div>
                    </div>
                    <div class="col-4">
                        <label for="name">Имя</label>
                        <input id="name" type="text" class="form-control" value="${(formData.student.name)!}" required>
                        <div class="invalid-tooltip">
                            Заполните поле
                        </div>
                    </div>
                    <div class="col-4">
                        <label for="patronymic">Отчество</label>
                        <input id="patronymic" type="text" class="form-control"
                               value="${(formData.student.patronymic)!}">
                    </div>
                </div>
                <div class="form-row mb-3">
                    <div class="col-6">
                        <label for="university_id">Университет</label>
                        <select id="university_id" class="custom-select" required>
                            <#list formData.universities as university>
                                <option value="${university.id}">${university.name}</option>
                            </#list>
                        </select>
                        <div class="invalid-tooltip">
                            Заполните поле
                        </div>
                    </div>
                    <div class="col-2">
                        <label for="course_id">Курс</label>
                        <select id="course_id" class="custom-select" required>
                            <#list formData.courses as course>
                                <option value="${course.id}">${course.value}</option>
                            </#list>
                        </select>
                        <div class="invalid-tooltip">
                            Заполните поле
                        </div>
                    </div>
                    <div class="col-4">
                        <label for="faculty">Факультет</label>
                        <input type="text" class="form-control" id="faculty" value="${(formData.student.faculty)!}"
                               required>
                        <div class="invalid-tooltip">
                            Заполните поле
                        </div>
                    </div>
                </div>
                <div class="form-row mb-3">
                    <div class="col-6">
                        <label for="email">Email</label>
                        <input type="text" class="form-control" id="email" value="${(formData.student.email)!}"
                               required>
                        <div class="invalid-tooltip">
                            Заполните поле
                        </div>
                    </div>
                    <div class="col-6">
                        <label for="phone">Номер телефона</label>
                        <input id="phone" type="text" class="form-control" value="${(formData.student.phone)!}">
                    </div>
                </div>
                <div class="form-row mb-3">
                    <div class="col-4">
                        <label for="science_direction_id">Направление</label>
                        <select id="science_direction_id" class="custom-select" required>
                            <#--                        <option hidden disabled selected value></option>-->
                            <#list formData.scienceDirections as scienceDirection>
                                <option value="${scienceDirection.id}">${scienceDirection.name}</option>
                            </#list>
                        </select>
                        <div class="invalid-tooltip">
                            Заполните поле
                        </div>
                    </div>
                    <div class="col-8">
                        <label for="topic">Тема</label>
                        <input type="text" class="form-control" id="topic" value="${(formData.student.topic)!}"
                               required>
                        <div class="invalid-tooltip">
                            Заполните поле
                        </div>
                    </div>
                </div>
                <div class="form-row mb-3">
                    <div class="col-9">
                        <label>Критерии</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="c1" value="${(formData.student.c1)!}" required
                                   onchange="changeCriteriaHandler(this)">
                            <input type="text" class="form-control" id="c2" value="${(formData.student.c2)!}" required
                                   onchange="changeCriteriaHandler(this)">
                            <input type="text" class="form-control" id="c3" value="${(formData.student.c3)!}" required
                                   onchange="changeCriteriaHandler(this)">
                            <input type="text" class="form-control" id="c4" value="${(formData.student.c4)!}" required
                                   onchange="changeCriteriaHandler(this)">
                            <input type="text" class="form-control" id="c5" value="${(formData.student.c5)!}" required
                                   onchange="changeCriteriaHandler(this)">
                            <input type="text" class="form-control" id="c6" value="${(formData.student.c6)!}" required
                                   onchange="changeCriteriaHandler(this)">
                            <input type="text" class="form-control" id="c7" value="${(formData.student.c7)!}" required
                                   onchange="changeCriteriaHandler(this)">
                            <input type="text" class="form-control" id="c8" value="${(formData.student.c8)!}" required
                                   onchange="changeCriteriaHandler(this)">
                            <input type="text" class="form-control" id="c9" value="${(formData.student.c9)!}" required
                                   onchange="changeCriteriaHandler(this)">
                            <input type="text" class="form-control" id="c10" value="${(formData.student.c10)!}" required
                                   onchange="changeCriteriaHandler(this)">
                            <input type="text" class="form-control" id="c11" value="${(formData.student.c11)!}" required
                                   onchange="changeCriteriaHandler(this)">
                            <input type="text" class="form-control" id="c12" value="${(formData.student.c12)!}" required
                                   onchange="changeCriteriaHandler(this)">
                            <input type="text" class="form-control" id="c13" value="${(formData.student.c13)!}" required
                                   onchange="changeCriteriaHandler(this)">
                            <input type="text" class="form-control" id="c14" value="${(formData.student.c14)!}" required
                                   onchange="changeCriteriaHandler(this)">
                            <input type="text" class="form-control" id="c15" value="${(formData.student.c15)!}" required
                                   onchange="changeCriteriaHandler(this)">
                        </div>
                    </div>
                    <div class="col-3">
                        <label>Пояснительная записка</label>
                        <div class="input-group">
                            <div class="custom-file">
                                <input type="file" class="custom-file-input" id="note" required>
                                <label class="custom-file-label" for="note" data-browse="Открыть">Выберите
                                    файл...</label>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="card-footer">
            <div class="d-flex">
                <div class="custom-control custom-checkbox mt-1">
                    <input id="permission" type="checkbox" form="studentForm" class="custom-control-input">
                    <label class="custom-control-label" for="permission">Разрешаю обработку своих персональных
                        данных</label>
                </div>
                <div class="ml-auto">
                    <button id="submitButton" class="btn btn-primary" form="studentForm" type="button"
                            onclick="submitStudentFormRegistration()" disabled>
                        Отправить
                    </button>
                    <#--                    <button id="submitButton" class="btn btn-primary" form="studentForm" type="submit" disabled>-->
                    <#--                        Отправить-->
                    <#--                    </button>-->
                </div>
            </div>
        </div>
    </div>
</#macro>