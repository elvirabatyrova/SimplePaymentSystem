// Generated by CoffeeScript 1.10.0
(function() {
  var MembersController, TasksController,
    extend = function(child, parent) { for (var key in parent) { if (hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; },
    hasProp = {}.hasOwnProperty,
    indexOf = [].indexOf || function(item) { for (var i = 0, l = this.length; i < l; i++) { if (i in this && this[i] === item) return i; } return -1; };

  MembersController = (function(superClass) {
    extend(MembersController, superClass);

    function MembersController() {
      MembersController.__super__.constructor.call(this);
      this.members = ["User1", "User2", "User3"];
      this.invalidMemberMessage = $("#invalid-member-error-message");
      this.updateAutocomplete();
    }

    MembersController.prototype.afterAddition = function() {
      return this.updateAutocomplete();
    };

    MembersController.prototype.afterRemove = function() {
      return this.updateAutocomplete();
    };

    MembersController.prototype.updateAutocomplete = function() {
      return this.newObjectInput.autocomplete({
        source: this.members.filter((function(_this) {
          return function(el) {
            return indexOf.call(_this.selectedMembers, el) < 0;
          };
        })(this))
      });
    };

    MembersController.prototype.addUser = function(username) {
      if (indexOf.call(this.members, username) >= 0) {
        return MembersController.__super__.addUser.call(this, username);
      } else {
        this.invalidMemberMessage.show();
        return this.formGroup.addClass("has-error");
      }
    };

    return MembersController;

  })(window.MembersController);

  TasksController = (function(superClass) {
    var cloneDisabled;

    extend(TasksController, superClass);

    cloneDisabled = function(obj, name) {
      return obj.clone().prop("disabled", true).attr("name", name);
    };

    function TasksController() {
      this.tasks = [];
      this.newTask = $("#new-task");
      this.newObjectInput = this.newTask.find("#new-task-args");
      this.optionsSelect = this.newObjectInput.siblings("select");
      this.tasksList = $("#tasks");
      this.bindEvents();
    }

    TasksController.prototype.bindEvents = function() {
      var endEdit;
      this.bindAddEvent("#add-task");
      this.tasksList.on("click", ".remove-task", function(e) {
        return $(this).parents(".task").remove();
      });
      this.tasksList.on("click", ".edit-task", function(e) {
        var i, input, inputs, len;
        $(this).siblings(".display-none").show();
        inputs = $(this).siblings("[disabled]");
        for (i = 0, len = inputs.length; i < len; i++) {
          input = inputs[i];
          $(input).data("previousVal", $(input).val());
        }
        inputs.attr("enabled", "");
        inputs.prop("disabled", false);
        return $(this).hide();
      });
      endEdit = function(context) {
        $(context).parent().find(".display-none").hide();
        $(context).siblings(".edit-task").show();
        return $(context).siblings("[enabled]").prop("disabled", true);
      };
      this.tasksList.on("click", ".cancel-edit", function(e) {
        var i, input, len, ref;
        ref = $(this).siblings("[enabled]");
        for (i = 0, len = ref.length; i < len; i++) {
          input = ref[i];
          $(input).val($(input).data("previousVal"));
        }
        return endEdit(this);
      });
      return this.tasksList.on("click", ".save", function(e) {
        return endEdit(this);
      });
    };

    TasksController.prototype.addObject = function() {
      if (this.optionsSelect.val() === "") {
        return;
      }
      return this.tasksList.append($("<li class='task'></li>").append(cloneDisabled(this.optionsSelect, "command").val(this.optionsSelect.val())).append(cloneDisabled(this.newObjectInput, "args").removeAttr("id").removeAttr("placeholder")).append("<button type='button' class='btn btn-default edit-task'><span class='glyphicon glyphicon-pencil'></span></button> <button type='button' class='btn btn-default display-none save'><span class='glyphicon glyphicon-ok'></span></button> <button type='button' class='btn btn-default display-none cancel-edit'><span class='glyphicon glyphicon-ban-circle'></span></button> <button type='button' class='btn btn-default remove-task'><span class='glyphicon glyphicon-remove'></span></button>"));
    };

    return TasksController;

  })(window.Controller);

  $(function() {
    new MembersController;
    new TasksController;
    return new window.DynamicForm().setName("input[name='member']", "username", "members").setName("select[name='command']", "command", "tasks").setName("input[name='args']", "args", "tasks");
  });

}).call(this);