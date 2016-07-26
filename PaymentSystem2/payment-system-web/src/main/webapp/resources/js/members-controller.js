// Generated by CoffeeScript 1.10.0
(function() {
  var MembersController,
    extend = function(child, parent) { for (var key in parent) { if (hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; },
    hasProp = {}.hasOwnProperty,
    indexOf = [].indexOf || function(item) { for (var i = 0, l = this.length; i < l; i++) { if (i in this && this[i] === item) return i; } return -1; };

  MembersController = (function(superClass) {
    extend(MembersController, superClass);

    function MembersController() {
      var member;
      this.membersDiv = $("#members");
      this.selectedMembers = (function() {
        var i, len, ref, results;
        ref = this.membersDiv.find(".member");
        results = [];
        for (i = 0, len = ref.length; i < len; i++) {
          member = ref[i];
          results.push($(member).attr("data-username"));
        }
        return results;
      }).call(this);
      this.messages = $(".help-block").hide();
      this.newMember = $("#new-member");
      this.formGroup = this.newMember.parent();
      this.newObjectInput = this.newMember.find("#new-member-username");
      this.memberDupMessage = $("#member-dup-warning-message");
      this.bindEvents();
    }

    MembersController.prototype.bindEvents = function() {
      this.bindAddEvent("#add-member");
      return this.membersDiv.on("click", ".remove-member", (function(_this) {
        return function(e) {
          var member, username;
          member = $(e.target).parents(".member");
          username = member.attr("data-username");
          _this.selectedMembers.splice(_this.selectedMembers.indexOf(username), 1);
          member.remove();
          return _this.afterRemove();
        };
      })(this));
    };

    MembersController.prototype.afterAddition = function() {};

    MembersController.prototype.afterRemove = function() {};

    MembersController.prototype.clearNewMemberForm = function() {
      this.messages.hide();
      return this.formGroup.removeClass("has-error has-warning has-success");
    };

    MembersController.prototype.addObject = function() {
      this.clearNewMemberForm();
      return this.addUser(this.newObjectInput.val());
    };

    MembersController.prototype.addUser = function(username) {
      if (!username) {
        return;
      }
      if (indexOf.call(this.selectedMembers, username) >= 0) {
        this.memberDupMessage.show();
        return this.formGroup.addClass("has-warning");
      } else {
        this.selectedMembers.push(username);
        this.membersDiv.append("<div class='input-group member' data-username='" + username + "'> <span class='input-group-addon'><span class='glyphicon glyphicon-user'></span></span> <input name='member' type='text' class='form-control' value='" + username + "' disabled> <span class='input-group-btn'> <button class='btn btn-default remove-member' type='button'><span class='glyphicon glyphicon-remove'></span></button> </span> </div>");
        this.newObjectInput.val("");
        this.formGroup.addClass("has-success");
        return this.afterAddition();
      }
    };

    return MembersController;

  })(window.Controller);

  window.MembersController = MembersController;

}).call(this);
