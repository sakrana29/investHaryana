(function(){
    'use strict';

    angular
        .module('investhryApp')
        .controller('serviceFormController', serviceFormController);

    serviceFormController.$inject = ['$timeout','$scope','$stateParams','$uibModalInstance','projectServiceFormFieldData','projectAttachemnt','projectServiceLog','ProjectAttachemnt','ProjectServiceLog','FileManagement','Projectservicedetail','Projectserviceformfielddata','serviceFormFieldDataCollection'];
    function serviceFormController ($timeout, $scope, $stateParams, $uibModalInstance,projectServiceFormFieldData, projectAttachemnt, projectServiceLog, ProjectAttachemnt, ProjectServiceLog, FileManagement, Projectservicedetail, Projectserviceformfielddata,serviceFormFieldDataCollection) {
        var vm = this;
        vm.projectServiceLog = projectServiceLog;
        vm.projectAttachemnt = projectAttachemnt;
        vm.projectServiceFormFieldData=projectServiceFormFieldData;
        vm.projectService = $stateParams.projectService;
        var projectAttachmentResultObject=null;

        vm.clear = clear;
        vm.saveFormFieldData = saveFormFieldData;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        loadServiceFormFields();
        function loadServiceFormFields() {
            vm.serviceFormFieldData= serviceFormFieldDataCollection.query({serviceid:vm.projectService.serviceid, projectid:vm.projectService.projectid});
            console.log(vm.serviceFormFieldData);
        }

        function saveFormFieldData() {
//                alert('helelo');
                var vmlength = vm.serviceFormFieldData.length;
//                alert(vmlength);
                for(var i= 0; i<vmlength;i++)
                {
                    var data = vm.serviceFormFieldData[i];
                    vm.isSaving = true;
                    data.projectid = vm.projectService.projectid;
                    if (data.id !== null) {
                        Projectserviceformfielddata.update(data, onSaveServiceFormFieldDataSuccess, onSaveServiceFormFieldDataError);
                    } else {
                        Projectserviceformfielddata.save(data, onSaveServiceFormFieldDataSuccess, onSaveServiceFormFieldDataError);
                    }
                    if(i === (vmlength-1))
                    {
                        saveProjectServiceLog();
                    }
                }
        }
        function onSaveServiceFormFieldDataSuccess(result)
        {}
        function onSaveServiceFormFieldDataError()
        {}
        function saveProjectServiceLog(result){
            $scope.$emit('investhryApp:projectserviceformfielddataUpdate', result);
            vm.projectServiceLog.projectid=vm.projectService.projectid;
            vm.projectServiceLog.serviceid=vm.projectService.serviceid;
            ProjectServiceLog.save(vm.projectServiceLog,onServiceLogSaveSuccess,onServiceLogSaveError);
        }
        function onServiceLogSaveSuccess(result)
        {
            $scope.$emit('investhryApp:projectServiceLogUpdate', result);
            vm.projectService.latestComments=vm.projectServiceLog.comments;
            vm.projectService.formFilledStatus=true;
            Projectservicedetail.update(vm.projectService,onUpdateProjectServiceSuccess,onUpdateProjectServiceError);
        }
        function onUpdateProjectServiceSuccess(result)
        {
//            alert('projectservicedetail saved');
            $scope.$emit('investhryApp:projectservicedetailUpdate', result);
            if(angular.isDefined(vm.projectAttachemnt.file)){
                saveProjectAttachment();
            }
            else
            {
//                alert('no file');
                $uibModalInstance.close(result);
                vm.isSaving = false;
            }
        }
        function onUpdateProjectServiceError()
        {
        alert('project service');
//            alert('project service not saved');
            vm.isSaving = false;
        }
        function onServiceLogSaveError()
        {
            alert('service log');
//            alert('Servicelog not saved');
            vm.isSaving = false;
        }
        function saveProjectAttachment()
        {
            var file=vm.projectAttachemnt.file;
            vm.projectAttachemnt.fileName=file.name;
            vm.projectAttachemnt.description="Service Form Field Data Attachment";
            vm.projectAttachemnt.fileExtension =file.type;
            ProjectAttachemnt.save(vm.projectAttachemnt,onSaveProjectAttachmentSuccess,onSaveProjectAttachmentError);
        }
        function onSaveProjectAttachmentSuccess(result)
        {
            $scope.$emit('investhryApp:projectAttachemntUpdate', result);
            var filename = result.id;
            var file =vm.projectAttachemnt.file;
            FileManagement.saveFile(file,filename);
            projectAttachmentResultObject=result;
            projectAttachmentResultObject.serverFileName=result.id;
            ProjectAttachemnt.update(projectAttachmentResultObject,onUpdateProjectAttachmentSuccess,onUpdateProjectAttachmentError);
        }
        function onSaveProjectAttachmentError()
        {
        alert('attachment error');
//            alert('project attachment not saved');
            vm.isSaving = false;
        }

        function onUpdateProjectAttachmentSuccess(result)
        {
//            alert('file attachment updated');
            $uibModalInstance.close(result);
        }
        function onUpdateProjectAttachmentError()
        {
            alert('attachment update error');
//            alert('file attachment not updated');
            vm.isSaving = false;
        }
    }
})();
