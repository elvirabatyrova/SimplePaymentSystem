// Generated by CoffeeScript 1.10.0
(function() {
  var AreaChart, Chart, ChartsCollection, DataConverter, GaugeChart, ProjectUpdater,
    extend = function(child, parent) { for (var key in parent) { if (hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; },
    hasProp = {}.hasOwnProperty,
    bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; };

  Array.prototype.last = function() {
    return this[this.length - 1];
  };

  Array.prototype.equals = function(another) {
    if (!(this && another)) {
      return;
    }
    if (this.length !== another.length) {
      return;
    }
    if (this.some(function(el, i) {
      if (Array.isArray(el)) {
        return !el.equals(another[i]);
      } else {
        return el !== another[i];
      }
    })) {
      return;
    }
    return true;
  };

  Chart = (function() {
    function Chart() {}

    Chart.prototype.load = function(data) {
      return this.chart.load({
        columns: [[this.title, data]]
      });
    };

    return Chart;

  })();

  GaugeChart = (function(superClass) {
    extend(GaugeChart, superClass);

    function GaugeChart(id, title, params) {
      var margin, margins;
      this.title = title;
      if (params == null) {
        params = {};
      }
      margins = Object.keys(params.colors);
      this.chart = c3.generate({
        bindto: id,
        data: {
          columns: [[this.title, 0]],
          type: "gauge"
        },
        gauge: {
          max: params.max ? params.max : 100
        },
        color: {
          pattern: (function() {
            var j, len, results;
            results = [];
            for (j = 0, len = margins.length; j < len; j++) {
              margin = margins[j];
              results.push(params.colors[margin]);
            }
            return results;
          })(),
          threshold: {
            values: margins
          }
        },
        size: {
          height: 180
        }
      });
    }

    GaugeChart.prototype.load = function(data) {
      if (Array.isArray(data)) {
        if (data.equals(this.data)) {
          return;
        }
        this.chart.internal.config.gauge_max = data[1];
        GaugeChart.__super__.load.call(this, data[0]);
      } else {
        if (data === this.data) {
          return;
        }
        GaugeChart.__super__.load.call(this, data);
      }
      return this.data = data;
    };

    return GaugeChart;

  })(Chart);

  AreaChart = (function(superClass) {
    extend(AreaChart, superClass);

    function AreaChart(id1) {
      this.id = id1;
    }

    AreaChart.prototype.load = function(data) {
      if (data.equals(this.data)) {
        return;
      }
      this.data = data;
      return this.chart = c3.generate({
        bindto: this.id,
        data: {
          columns: data,
          type: "area"
        }
      });
    };

    return AreaChart;

  })(Chart);

  ChartsCollection = (function() {
    function ChartsCollection(charts1) {
      this.charts = charts1;
    }

    ChartsCollection.prototype.update = function(data) {
      var chart, i, j, len, ref, results;
      ref = this.charts;
      results = [];
      for (i = j = 0, len = ref.length; j < len; i = ++j) {
        chart = ref[i];
        results.push(chart.load(data[i]));
      }
      return results;
    };

    return ChartsCollection;

  })();

  DataConverter = (function() {
    function DataConverter() {}

    DataConverter.convert = function(data) {
      var build;
      return [
        [data.builds.last().passedTests, data.builds.last().testsAmount], data.builds.last().testCoverage, [
          ["Build time"].concat((function() {
            var j, len, ref, results;
            ref = data.builds;
            results = [];
            for (j = 0, len = ref.length; j < len; j++) {
              build = ref[j];
              results.push(build.time);
            }
            return results;
          })())
        ], [
          ["Test coverage"].concat((function() {
            var j, len, ref, results;
            ref = data.builds;
            results = [];
            for (j = 0, len = ref.length; j < len; j++) {
              build = ref[j];
              results.push(build.testCoverage);
            }
            return results;
          })()), ["Passed tests"].concat((function() {
            var j, len, ref, results;
            ref = data.builds;
            results = [];
            for (j = 0, len = ref.length; j < len; j++) {
              build = ref[j];
              results.push((build.passedTests / build.testsAmount) * 100);
            }
            return results;
          })())
        ]
      ];
    };

    return DataConverter;

  })();

  ProjectUpdater = (function(superClass) {
    extend(ProjectUpdater, superClass);

    function ProjectUpdater(charts1, data1) {
      this.charts = charts1;
      this.data = data1;
      this.update = bind(this.update, this);
      this.branch = "master";
      this.builds = $("#builds");
      this.branches = $("#branches");
      ProjectUpdater.__super__.constructor.call(this);
    }

    ProjectUpdater.prototype.update = function() {
      this.updateCharts();
      this.updateBuilds();
      return this.updateBranches();
    };

    ProjectUpdater.prototype.updateCharts = function() {
      return this.charts.update(DataConverter.convert(this.data[this.branch]));
    };

    ProjectUpdater.prototype.updateBuilds = function() {
      var build, i, j, len, ref, results;
      this.builds.empty();
      ref = this.data[this.branch].builds;
      results = [];
      for (i = j = 0, len = ref.length; j < len; i = ++j) {
        build = ref[i];
        results.push(this.builds.append("<tr class='" + (build.passed ? 'success' : 'danger') + "'><td>" + i + "</td><td>" + build.commit + "</td><td>" + (build.passed ? 'Passed' : 'Failed') + "</td></tr>"));
      }
      return results;
    };

    ProjectUpdater.prototype.updateBranches = function() {
      var branch, j, len, ref, results;
      this.branches.empty();
      ref = Object.keys(this.data);
      results = [];
      for (j = 0, len = ref.length; j < len; j++) {
        branch = ref[j];
        results.push(this.branches.append("<tr><td class='branch " + (branch === this.branch ? 'active' : void 0) + "'><a href='#' data-branch='" + branch + "'>" + branch + "</a></td><td class='" + (this.data[branch].builds.last().passed ? 'success' : 'danger') + "'><a href='#'>" + (this.data[branch].builds.last().passed ? 'passed' : 'failed') + "</a></td></tr>"));
      }
      return results;
    };

    ProjectUpdater.prototype.setBranch = function(branch1) {
      this.branch = branch1;
      return this.update();
    };

    return ProjectUpdater;

  })(window.Updater);

  $(function() {
    var addBranch, changeMaster, charts, data, updater;
    data = {
      master: {
        builds: [
          {
            commit: "Commit M1",
            passed: false,
            time: 32,
            testCoverage: 80,
            passedTests: 28,
            testsAmount: 30
          }, {
            commit: "Commit M2",
            passed: false,
            time: 38,
            testCoverage: 88,
            passedTests: 31,
            testsAmount: 33
          }, {
            commit: "Commit M3",
            passed: true,
            time: 40,
            testCoverage: 85,
            passedTests: 32,
            testsAmount: 32
          }
        ]
      },
      "new-feature": {
        builds: [
          {
            commit: "Commit NF1",
            passed: true,
            time: 40,
            testCoverage: 90,
            passedTests: 33,
            testsAmount: 33
          }, {
            commit: "Commit NF2",
            passed: false,
            time: 38,
            testCoverage: 75,
            passedTests: 31,
            testsAmount: 34
          }
        ]
      }
    };
    charts = new ChartsCollection([
      new GaugeChart("#passed-tests-chart", "Passed tests", {
        colors: {
          100: "#60B044"
        }
      }), new GaugeChart("#test-coverage-chart", "Test coverage", {
        colors: {
          40: "#FF0000",
          60: "#F97600",
          80: "#F6C600",
          100: "#60B044"
        }
      }), new AreaChart("#build-time-chart"), new AreaChart("#tests-chart")
    ]);
    updater = new ProjectUpdater(charts, data);
    $("#branches").on("click", "[data-branch]", function(e) {
      e.preventDefault();
      $(".branch.active").removeClass("active");
      $(this).parent().addClass("active");
      return updater.setBranch($(this).attr("data-branch"));
    });
    changeMaster = function() {
      return data.master.builds.push({
        commit: "Commit M4",
        passed: false,
        time: 38,
        testCoverage: 85,
        passedTests: 31,
        testsAmount: 32
      });
    };
    setTimeout(changeMaster, 3000);
    addBranch = function() {
      return data["fix-bug"] = {
        builds: [
          {
            commit: "Commit FB1",
            passed: true,
            time: 32,
            testCoverage: 90,
            passedTests: 34,
            testsAmount: 34
          }
        ]
      };
    };
    return setTimeout(addBranch, 5000);
  });

}).call(this);