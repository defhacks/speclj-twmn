(ns speclj.report.twmn
  (:require [speclj.results :refer [categorize]]
            [speclj.platform :refer [format-seconds]]
            [speclj.reporting :refer [tally-time]]
            [speclj.report.progress :refer [describe-counts-for]]
            [clojure.java.io :refer [input-stream resource as-url]])
  (:use     [clojure.java.shell :only [sh]])
  (:import [speclj.reporting Reporter]))

(def ^:private resource-stream (comp input-stream resource))

(defn- settings [type]
  (case type
    :pass {:name "Success" :icon "/usr/share/icons/gnome/48x48/emblems/emblem-default.png" }
    :fail {:name "Failure" :icon "/usr/share/icons/gnome/48x48/emotes/face-crying.png" }
    :error  {:name "Error" :icon "/usr/share/icons/gnome/48x48/emotes/face-surprise.png"}))

(defn twmn
  "Trigger notification of the appropriate type"
  [result message]
    (let [params (settings result)]
      (sh "twmnc" "-c" message "-t" (params :name) "-i" (params :icon))))

(defn twmn-message [results]
  (let [result-map (categorize results)
        result     (if (= 0 (count (:fail result-map))) :pass :fail)
        seconds    (format-seconds (tally-time results))
        outcome    (describe-counts-for result-map)
        message    (format "%s\nTook %s seconds" outcome seconds)]
    (twmn result message)))

(deftype TwmnReporter []
    Reporter
    (report-message [this message])
    (report-description [this description])
    (report-pass [this result])
    (report-pending [this result])
    (report-fail [this result])
    (report-runs [this results] (twmn-message results))
    (report-error [this error]
      (let [exception (.-exception error)]
        (twmn :error (format "%s: %s"
                              (.getSimpleName (class exception))
                              (.getMessage exception))))))

(defn new-twmn-reporter []
    (TwmnReporter.))
