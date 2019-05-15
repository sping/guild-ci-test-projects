//
//  AppUtils.swift
//  GitViewer
//
//  Created by Sebastiaan Seegers on 14/05/2019.
//  Copyright © 2019 Sping. All rights reserved.
//

import UIKit
import Fabric
import Crashlytics
import SAMKeychain
import Reachability
import SVProgressHUD
import IQKeyboardManagerSwift

class AppUtils {
  static let shared = AppUtils()

  var reachability: Reachability?

  func setupAppDependencies() {
    setupFabric()
    setupProgressHud()
    setupInternetMonitoring()
    IQKeyboardManager.shared.enable = true
  }

  private func setupFabric() {
    Fabric.with([Crashlytics.self, Answers.self])
  }

  private func setupInternetMonitoring() {
    reachability = Reachability()
  }

  private func setupProgressHud() {
    SVProgressHUD.setRingRadius(24.0)
    SVProgressHUD.setRingThickness(5.0)
    SVProgressHUD.setDefaultMaskType(.clear)
    SVProgressHUD.setBackgroundColor(UIColor(hex: "#E6FCFF"))
    SVProgressHUD.setForegroundColor(UIColor(hex: "#F8CD46"))
  }

  func userToken() -> String {
    return SAMKeychain.password(forService: SAMKEYCHAINSERVICENAME, account: SAMKEYCHAINACCOUNT) ?? ""
  }

  func setUser(Creds creds: String) {
    SAMKeychain.setPassword(creds, forService: SAMKEYCHAINSERVICENAME, account: SAMKEYCHAINACCOUNT)
    BaseService.resetManagers()
  }

  /// Method to check if the device is connected to the internet
  func hasInternet() -> Bool {
    return reachability == nil ? false : (reachability!.connection != .none)
  }
}
