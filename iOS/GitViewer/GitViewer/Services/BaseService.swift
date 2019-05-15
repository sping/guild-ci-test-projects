//
//  BaseService.swift
//  GitViewer
//
//  Created by Sebastiaan Seegers on 14/05/2019.
//  Copyright © 2019 Sping. All rights reserved.
//

import Alamofire
import Foundation

class BaseService {
  static var manager = BaseService.getManager()
  static var cachedManager = BaseService.getCachedManager()
  static var urlCache = URLCache(memoryCapacity: 10 * 1024 * 1024, diskCapacity: 50 * 1024 * 1024, diskPath: nil)

  static func getManager() -> SessionManager {
    let configuration = defaultSessionConfiguration()
    configuration.urlCache = nil
    configuration.requestCachePolicy = .reloadIgnoringLocalCacheData
    return Alamofire.SessionManager(configuration: configuration)
  }

  static func getCachedManager() -> SessionManager {
    let configuration = defaultSessionConfiguration()
    configuration.urlCache = urlCache
    configuration.requestCachePolicy = .returnCacheDataElseLoad
    return Alamofire.SessionManager(configuration: configuration)
  }

  static func resetManagers() {
    self.manager = BaseService.getManager()
    self.cachedManager = BaseService.getCachedManager()
  }

  private static func defaultSessionConfiguration() -> URLSessionConfiguration {
    var defaultHeaders = Alamofire.SessionManager.default.session.configuration.httpAdditionalHeaders ?? [:]
    defaultHeaders["Authorization"] = "Basic \(AppUtils.shared.userToken())"

    let configuration = URLSessionConfiguration.default
    configuration.httpAdditionalHeaders = defaultHeaders

    return configuration
  }

//  // MARK: - Error handling helpers
//
//  static func apiErrorFor(_ data: Data, error: NSError, params: [String: AnyObject]?) -> ApiErrorResponse {
//    let apiError = ApiErrorResponse(data: data, error: error, params: params)
//    if apiError.message == nil {
//      apiError.message = error.localizedDescription
//    }
//    return apiError
//  }
}
