(defproject speclj-twmn"0.0.1"
  :description "Twmn reporter for the speclj testing framework"
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :resource-paths ["resources"]
  :url "https://github.com/defhacks/speclj-twmn"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :profiles {
    :dev {
      :plugins [[speclj "2.7.2"]]
      :dependencies [[speclj "2.7.2"]]
      :test-paths ["spec/"]}})
