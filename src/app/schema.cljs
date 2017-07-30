
(ns app.schema )

(def store {:states {}, :router {:name :task-list}, :tasks {}, :tags {}})

(def task {:id nil, :text "", :done? false, :details "", :tag-ids #{}})

(def tag {:id nil, :name "", :hidden? false})
