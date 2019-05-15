# Uncomment the next line to define a global platform for your project
 platform :ios, '11.0'

target 'GitViewer' do
  # Comment the next line if you're not using Swift and don't want to use dynamic frameworks
  use_frameworks!

  # Pods for GitViewer
  pod 'Fabric'
  pod 'SwiftLint'
  pod 'Alamofire'
  pod 'SwiftyJSON'
  pod 'PopupDialog'
  pod 'Crashlytics'
  pod 'SAMKeychain'
  pod 'SVProgressHUD'
  pod 'ReachabilitySwift'
  pod 'IQKeyboardManagerSwift'
end

# Adding post_install hook to make sure Build Active Architectures is always set to NO
post_install do |installer_representation|
  installer_representation.pods_project.targets.each do |target|
    target.build_configurations.each do |config|
      config.build_settings['ONLY_ACTIVE_ARCH'] = 'NO'
    end
  end
end
