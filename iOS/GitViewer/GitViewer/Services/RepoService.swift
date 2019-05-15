//
//  RepoService.swift
//  GitViewer
//
//  Created by Sebastiaan Seegers on 14/05/2019.
//  Copyright © 2019 Sping. All rights reserved.
//

import Alamofire
import Foundation
import SwiftyJSON

class RepoService: BaseService {
  static func getRepos(completionHandler: @escaping(_ repos: [GithubRepo]?, _ error: Bool) -> Void) {
    let url = "\(APIURL)/user/repos"

    manager.request(url, method: .get, encoding: JSONEncoding.default)
      .validate()
      .responseJSON { response in
        switch response.result {
        case .success:
          print("Got repo's")

          if let value = response.result.value {
            let json = JSON(value)
            var repos: [GithubRepo] = []
            for item in json.arrayValue {
              let name = item["name"].stringValue
              let url = item["url"].stringValue
              repos.append(GithubRepo(RepoName: name, Url: url))
            }

            completionHandler(repos, false)
          }
        case .failure(let error):
          print("Failed getting repo's \(error.localizedDescription)")
          completionHandler(nil, true)
        }
    }
  }
}
