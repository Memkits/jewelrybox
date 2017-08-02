
(ns app.schema )

(def store {:states {}, :router {:name :task-list}, :tasks {}, :tags {}})

(def task {:id nil, :text "", :done? false, :details "", :tag-ids #{}, :created-time nil})

(def tag {:id nil, :name "", :detail "", :hidden? false, :created-time nil})
