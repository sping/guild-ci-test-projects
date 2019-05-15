//
//  BaseService.swift
//  GitViewer
//
//  Created by Sebastiaan Seegers on 14/05/2019.
//  Copyright © 2019 Sping. All rights reserved.
//

import Alamofire
import Foundation
import SwiftyJSON

class AuthenticationService: BaseService {

  static func authenticate(Username username: String, Password password: String, completionHandler: @escaping (_ success: Bool) -> Void) {
    print("Going to login to Github!")

    let url = "\(APIURL)/user/repos"
    let credentialData = "\(username):\(password)".data(using: .utf8)
    let base64Credentials = credentialData!.base64EncodedString()
    let headers = ["Authorization": "Basic \(base64Credentials)"]

    Alamofire.request(url, method: .get, encoding: JSONEncoding.default, headers: headers)
      .validate()
      .responseJSON { response in
        switch response.result {
        case .success:
          print("Successfully logged in to Github!")
          AppUtils.shared.setUser(Creds: base64Credentials)
          completionHandler(true)

        case .failure(let error):
          print("Error logging into github \(error.localizedDescription)")
          completionHandler(false)
        }
    }
  }
}
