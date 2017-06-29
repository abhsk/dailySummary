package dailySummary.controller;

import dailySummary.contract.*;
import dailySummary.dto.DailySummaryContractToModelDTO;
import dailySummary.error.NotAMemberError;
import dailySummary.model.Member;
import dailySummary.service.DailySummaryService;
import dailySummary.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DailySummaryController {

    @Autowired
    private DailySummaryService dailySummaryService;

    @Autowired
    private MailService mailService;

    @Autowired
    private DailySummaryContractToModelDTO dailySummaryContractToModelDTO;

    @RequestMapping(value = "/dailySummary/save", method = RequestMethod.POST)
    public ResponseEntity getCountryPage(@RequestBody DailySummary summary) {
        dailySummaryService.persistSummary(dailySummaryContractToModelDTO.toDailySummaryModel(summary));
        return new ResponseEntity(summary, HttpStatus.OK);
    }

    @RequestMapping(value = "/addMember", method = RequestMethod.POST)
    public ResponseEntity addMember(@RequestBody AddMemberRequest addMemberRequest) {
        dailySummaryService.addMember(dailySummaryContractToModelDTO.toMemberModel(addMemberRequest));
        return new ResponseEntity(addMemberRequest, HttpStatus.OK);
    }

    @RequestMapping(value = "/createTeam", method = RequestMethod.POST)
    public ResponseEntity createTeam(@RequestBody AddTeamRequest addTeamRequest) {
        dailySummaryService.createTeam(dailySummaryContractToModelDTO.toTeamModel(addTeamRequest));
        return new ResponseEntity(addTeamRequest, HttpStatus.OK);
    }

    @RequestMapping("/team/getAll")
    public ResponseEntity getAllTeams() {
        return new ResponseEntity(TeamResponse.builder().teams(dailySummaryService.getAllTeams()).build(), HttpStatus.OK);
    }
    @RequestMapping(value = "/preview", method = RequestMethod.POST)
    public ResponseEntity getMailPreviewForATeam(@RequestBody RequestByEmail requestByEmail) {
        return new ResponseEntity(mailService.preview(requestByEmail.getEmailId()), HttpStatus.OK);
    }


    @RequestMapping(value = "/team", method = RequestMethod.POST)
    public ResponseEntity getTeamForAUser(@RequestBody RequestByEmail requestByEmail) throws NotAMemberError {
        Member member = dailySummaryService.getTeamForAUser(requestByEmail.getEmailId());
        return new ResponseEntity(member, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllMembers", method = RequestMethod.POST)
    public ResponseEntity getAllMembersForATeam(@RequestBody RequestByEmail requestByEmail) throws NotAMemberError {
        List<Member> members = dailySummaryService.getAllUsersForATeam(requestByEmail.getEmailId());
        return new ResponseEntity(members, HttpStatus.OK);
    }
}
