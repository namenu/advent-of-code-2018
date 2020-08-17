// Generated by BUCKLESCRIPT, PLEASE EDIT WITH CARE
'use strict';

var Util = require("./util");
var $$String = require("bs-platform/lib/js/string.js");
var Belt_List = require("bs-platform/lib/js/belt_List.js");
var Pervasives = require("bs-platform/lib/js/pervasives.js");

function gets(prim) {
  return Util.gets(prim);
}

function solve(cheatSheet, n) {
  var lines = $$String.split_on_char(/* "\n" */10, $$String.trim(cheatSheet));
  return Belt_List.getBy(Belt_List.keepMap(lines, (function (l) {
                    var match = $$String.split_on_char(/* " " */32, l);
                    if (!match) {
                      return ;
                    }
                    var match$1 = match.tl;
                    if (match$1 && !match$1.tl) {
                      return Pervasives.int_of_string_opt(match$1.hd);
                    }
                    
                  })), (function (x) {
                return x > n;
              }));
}

function part2(param) {
  return Util.gets("https://oeis.org/A141481/b141481.txt").then(function (value) {
                var answer = solve(value, 312051);
                console.log(answer);
                return Promise.resolve(answer);
              }).catch(function (e) {
              console.log(e);
              return Promise.resolve(undefined);
            });
}

part2(undefined);

var input = "#A141481: Square spiral of sums of selected preceding terms, starting at 1..\n#Table of n, a(n) for n = 1..961\n1 1\n2 1\n3 2\n4 4\n5 5\n6 10\n7 11\n8 23\n9 25\n10 26\n11 54\n12 57\n13 59\n14 122\n15 133\n16 142\n17 147\n18 304\n19 330\n20 351\n21 362";

exports.gets = gets;
exports.input = input;
exports.solve = solve;
exports.part2 = part2;
/*  Not a pure module */
